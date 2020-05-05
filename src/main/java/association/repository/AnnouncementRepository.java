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
	
	/*
	 * @Query("SELECT z FROM Zadatak z WHERE " +
	 * "(:ime IS NULL or z.ime = :ime ) AND " +
	 * "(:sprintId IS NULL or z.sprint.id = :sprintId )" ) Page<Announcement>
	 * search(
	 * 
	 * @Param("ime") String ime,
	 * 
	 * @Param("sprintId") Long sprintId, Pageable pageRequest);
	 */
	
}
