package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Message;
import association.model.Vote;
import association.service.MessageService;
import association.service.VoteService;
import association.web.dto.VoteDTO;

@Component
public class VoteDTOToVote implements Converter<VoteDTO, Vote> {

	@Autowired
	private VoteService voteService;
	@Autowired
	private MessageService messageService;

	@Override
	public Vote convert(VoteDTO dto) {

		Message message = messageService.findOne(dto.getMessageId());

		if (message != null) {
			Vote vote = null;

			if (dto.getId() != null) {
				vote = voteService.findOne(dto.getId());
			} else {
				vote = new Vote();
			}

			vote.setAccept(dto.getAccept());

			vote.setMessage(message);

			return vote;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Vote> convert(List<VoteDTO> dtoList) {
		List<Vote> ret = new ArrayList<>();

		for (VoteDTO dto : dtoList) {
			ret.add(convert(dto));
		}

		return ret;
	}

}
