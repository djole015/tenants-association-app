package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Message;
import association.model.Flat;
import association.service.MessageService;
import association.service.FlatService;
import association.web.dto.MessageDTO;

@Component
public class MessageDTOToMessage implements Converter<MessageDTO, Message> {

	@Autowired
	private MessageService messageService;

	@Autowired
	private FlatService flatService;

	@Override
	public Message convert(MessageDTO dto) {
		Flat flat = flatService.findOne(dto.getFlatId());

		if (flat != null) {
			Message message = null;

			if (dto.getId() != null) {
				message = messageService.findOne(dto.getId());
			} else {
				message = new Message();
			}

			message.setTitle(dto.getTitle());
			message.setDescription(dto.getDescription());
			message.setType(dto.getType());
			message.setAccepted(dto.isAccepted());
			message.setPercentageNeeded(dto.getPercentageNeeded());

			message.setFlat(flat);

			return message;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Message> convert(List<MessageDTO> dtoList) {
		List<Message> ret = new ArrayList<>();

		for (MessageDTO dto : dtoList) {
			ret.add(convert(dto));
		}

		return ret;
	}
}
