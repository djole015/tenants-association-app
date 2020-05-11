package association.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import association.model.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{

	  List<Announcement> findByFlatId(Long flatId);


	  @Query("SELECT a FROM Announcement a WHERE " +
	  "(:title IS NULL or a.title LIKE :title ) AND " +
	  "(:type IS NULL or a.type LIKE :type ) AND " +
	  "(:flatId IS NULL or a.flat.id = :flatId )" )
	  Page<Announcement> search(
			@Param("title") String title, 
			@Param("type") String type, 
			@Param("flatId") Long flatId, 
			Pageable pageRequest);
	
}
