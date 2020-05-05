package association.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import association.model.Flat;
import association.repository.FlatRepository;
import association.service.FlatService;

@Service
@Transactional
public class JpaFlatService implements FlatService {

	@Autowired
	private FlatRepository flatRepository;
	
	@Override
	public List<Flat> findAll() {
		return flatRepository.findAll();
	}

	@Override
	public Flat save(Flat flat) {
		return flatRepository.save(flat);
	}

	@Override
	public Flat findOne(Long id) {
		return flatRepository.findOne(id);
	}

}
