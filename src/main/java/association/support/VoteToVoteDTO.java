package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Vote;
import association.web.dto.VoteDTO;

@Component
public class VoteToVoteDTO implements Converter<Vote, VoteDTO> {

	@Override
	public VoteDTO convert(Vote source) {
		VoteDTO dto = new VoteDTO();

		dto.setId(source.getId());
		dto.setAccept(source.getAccept());
		
		dto.setMessageId(source.getMessage().getId());

		return dto;
	}

	public List<VoteDTO> convert(List<Vote> sourceList) {
		List<VoteDTO> dtoList = new ArrayList<>();

		for (Vote source : sourceList) {
			dtoList.add(convert(source));
		}

		return dtoList;
	}

}
