package association.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import association.model.Vote;
import association.service.VoteService;
import association.support.VoteToVoteDTO;
import association.web.dto.VoteDTO;

@RestController
@RequestMapping(value="/api/votes")
public class ApiVoteController {
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private VoteToVoteDTO toDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<VoteDTO>> getVotes() {
		
		List<Vote> votes = null;
		
		votes = voteService.findAll();

	
		return new ResponseEntity<>(
				toDTO.convert(votes),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<VoteDTO> get(
			@PathVariable Long id){
		
		Vote vote = voteService.findOne(id);
		
		if(vote == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(vote),
				HttpStatus.OK);
	}
	
}
