package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Message;
import association.web.dto.MessageDTO;

@Component
public class MessageToMessageDTO implements Converter<Message, MessageDTO> {

	@Override
	public MessageDTO convert(Message source) {
		MessageDTO dto = new MessageDTO();

		dto.setId(source.getId());
		dto.setTitle(source.getTitle());
		dto.setDescription(source.getDescription());
		dto.setAccepted(source.isAccepted());
		dto.setType(source.getType());
		dto.setPercentageNeeded(source.getPercentageNeeded());

		dto.setFlatId(source.getFlat().getId());
		dto.setFlatAddress(source.getFlat().getAddress());
		dto.setFlatNoOfTenants(source.getFlat().getNoOfTenants());
		
		dto.setNoOfVotes(source.getVotes().size());

		return dto;
	}

	public List<MessageDTO> convert(List<Message> sourceList) {
		List<MessageDTO> dtoList = new ArrayList<>();

		for (Message source : sourceList) {
			dtoList.add(convert(source));
		}

		return dtoList;
	}

}
