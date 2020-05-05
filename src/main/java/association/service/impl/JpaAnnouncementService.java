package association.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import association.model.Announcement;
import association.repository.AnnouncementRepository;
import association.service.AnnouncementService;

@Service
public class JpaAnnouncementService implements AnnouncementService {

	@Autowired
	private AnnouncementRepository announcementRepository;

	@Override
	public Page<Announcement> findAll(int pageNum) {
		return announcementRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Announcement findOne(Long id) {
		return announcementRepository.findOne(id);
	}

	@Override
	public Announcement save(Announcement announcement) {
		return announcementRepository.save(announcement);
	}

	@Override
	public Announcement delete(Long id) {
		Announcement announcement = announcementRepository.findOne(id);

		if (announcement != null) {
			announcementRepository.delete(id);
		}

		return announcement;
	}

	@Override
	public List<Announcement> findByFlatId(Long flatId) {
		return announcementRepository.findByFlatId(flatId);
	}

}
