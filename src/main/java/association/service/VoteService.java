package association.service;

import java.util.List;

import association.model.Vote;

public interface VoteService {

	List<Vote> findAll();

	Vote save(Vote stanje);

	Vote findOne(Long id);
}
