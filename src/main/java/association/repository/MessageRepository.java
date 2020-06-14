package association.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import association.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	  List<Message> findByFlatId(Long flatId);


	  @Query("SELECT m FROM Message m WHERE " +
	  "(:title IS NULL or m.title LIKE :title ) AND " +
	  "(:type IS NULL or m.type LIKE :type ) AND " +
	  "(:flatId IS NULL or m.flat.id = :flatId )" )
	  Page<Message> search(
			@Param("title") String title, 
			@Param("type") String type, 
			@Param("flatId") Long flatId, 
			Pageable pageRequest);
	
}
