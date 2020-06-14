package association.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import association.model.Message;
import association.service.MessageService;
import association.support.MessageDTOToMessage;
import association.support.MessageToMessageDTO;
import association.web.dto.MessageDTO;

@RestController
@RequestMapping(value = "/api/messages")
public class ApiMessageController {
	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageToMessageDTO toDTO;

	@Autowired
	MessageDTOToMessage toMessage;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<MessageDTO>> getMessages(@RequestParam(required = false) String title,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long flatId,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Message> messagesPage;

		if (title != null || type != null || flatId != null) {
			messagesPage = messageService.search(title, type, flatId, pageNum);
		} else {

			messagesPage = messageService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(messagesPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(messagesPage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<MessageDTO> getMessage(@PathVariable Long id) {
		Message message = messageService.findOne(id);
		if (message == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(message), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<MessageDTO> delete(@PathVariable Long id) {
		Message deleted = messageService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO> add(@Validated @RequestBody MessageDTO newMessageDTO) {

		Message savedMessage = messageService.save(toMessage.convert(newMessageDTO));

		return new ResponseEntity<>(toDTO.convert(savedMessage), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<MessageDTO> edit(@Validated @RequestBody MessageDTO messageDTO,
			@PathVariable Long id) {

		if (!id.equals(messageDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Message persisted = messageService.save(toMessage.convert(messageDTO));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
