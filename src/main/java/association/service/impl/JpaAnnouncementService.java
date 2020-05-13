package association.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import association.model.Announcement;
import association.model.Vote;
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

	@Override
	public Page<Announcement> search(String title, String type, Long flatId, int pageNum) {

		if (title != null) {
			title = "%" + title + "%";
		}

		if (type != null) {
			type = "%" + type + "%";
		}

		return announcementRepository.search(title, type, flatId, new PageRequest(pageNum, 5));
	}

	@Override
	public void performCheck(Vote savedVote) {

		int positiveVotes = 0;

		Announcement announcement = savedVote.getAnnouncement();

		double percentageneeded = announcement.getPercentageNeeded();

		int numberOfTenants = announcement.getFlat().getNoOfTenants();

		List<Vote> votes = announcement.getVotes();

		for (Vote vote : votes) {
			if (vote.getAccept().equals("yes")) {
				positiveVotes++;
			}
		}

		if ((positiveVotes / numberOfTenants) * 100 >= percentageneeded) {
			announcement.setAccepted(true);
			announcementRepository.save(announcement);
		}

	}

}
