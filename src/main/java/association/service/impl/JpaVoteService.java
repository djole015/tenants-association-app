package association.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import association.model.Vote;
import association.repository.VoteRepository;
import association.service.VoteService;

@Service
@Transactional
public class JpaVoteService implements VoteService {

	@Autowired
	private VoteRepository voteRepository;
	
	@Override
	public List<Vote> findAll() {
		return voteRepository.findAll();
	}

	@Override
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}

	@Override
	public Vote findOne(Long id) {
		return voteRepository.findById(id).get();
	}

}
