package association.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import association.model.Flat;


@Repository
public interface FlatRepository extends JpaRepository<Flat, Long>{

}
