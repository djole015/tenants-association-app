package association.service;

import java.util.List;

import association.model.Flat;

public interface FlatService {

	List<Flat> findAll();

	Flat save(Flat flat);

	Flat findOne(Long id);
}
