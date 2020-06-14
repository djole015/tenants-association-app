package association.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import association.model.Message;
import association.model.Flat;
import association.service.MessageService;
import association.service.FlatService;
import association.support.MessageToMessageDTO;
import association.support.FlatToFlatDTO;
import association.web.dto.MessageDTO;
import association.web.dto.FlatDTO;

@RestController
@RequestMapping(value = "/api/flats")
public class ApiFlatController {
	@Autowired
	private FlatService flatService;

	@Autowired
	private FlatToFlatDTO toDTO;

	@Autowired
	private MessageToMessageDTO toMessageDTO;

	@Autowired
	private MessageService messageService;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<FlatDTO>> getFlats() {

		List<Flat> flats = null;

		flats = flatService.findAll();

		return new ResponseEntity<>(toDTO.convert(flats), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FlatDTO> get(@PathVariable Long id) {

		Flat flat = flatService.findOne(id);

		if (flat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(flat), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
	ResponseEntity<List<MessageDTO>> getMessages(@PathVariable("id") Long flatId) {

		List<Message> messages = messageService.findByFlatId(flatId);

		if (messages == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toMessageDTO.convert(messages), HttpStatus.OK);
	}

}
