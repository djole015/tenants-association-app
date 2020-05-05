package association.service;

import java.util.List;

import org.springframework.data.domain.Page;

import association.model.Announcement;

public interface AnnouncementService {

	Page<Announcement> findAll(int pageNum);

	Announcement findOne(Long id);

	Announcement save(Announcement announcement);

	Announcement delete(Long id);

	List<Announcement> findByFlatId(Long flatId);

}
