package association;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import association.model.Flat;
import association.model.Vote;
import association.model.Announcement;
import association.service.FlatService;
import association.service.VoteService;
import association.service.AnnouncementService;

@Component
public class TestData {
	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private FlatService flatService;

	@PostConstruct
	public void init() {

		Flat f1 = new Flat();
		f1.setAddress("Prote Smiljanica 48");
		f1.setPresident("Mika Mikic");
		f1.setNoOfApartments(42);
		f1.setNoOfTenants(99);

		flatService.save(f1);

		Flat f2 = new Flat();
		f2.setAddress("Vlade Jovanovica 2");
		f2.setPresident("Djoka Djokic");
		f2.setNoOfApartments(21);
		f2.setNoOfTenants(55);

		flatService.save(f2);

		Flat f3 = new Flat();
		f3.setAddress("Cerska 3");
		f3.setPresident("Laza Lazic");
		f3.setNoOfApartments(64);
		f3.setNoOfTenants(120);

		flatService.save(f3);

		Announcement a1 = new Announcement();
		a1.setTitle("finance");
		a1.setDescription("empty fund");
		a1.setType("notice");
		a1.setFlat(f1);

		announcementService.save(a1);

		Announcement a2 = new Announcement();
		a2.setTitle("lock replacement");
		a2.setDescription("6/15 locksmith comming");
		a2.setType("notice");
		a2.setFlat(f1);

		announcementService.save(a2);

		Announcement a3 = new Announcement();
		a3.setTitle("no water/low pressure");
		a3.setDescription("6/11 from 8am-10am");
		a3.setType("notice");
		a3.setFlat(f2);

		announcementService.save(a3);

		Announcement a4 = new Announcement();
		a4.setTitle("facade painting");
		a4.setDescription("in favor or against");
		a4.setType("proposal");
		a4.setPercentageNeeded(66D);
		a4.setFlat(f3);

		announcementService.save(a4);

		Vote v1 = new Vote();
		v1.setAccept("yes");
		v1.setAnnouncement(a2);

		voteService.save(v1);

		Vote v2 = new Vote();
		v2.setAccept("no");
		v2.setAnnouncement(a4);

		voteService.save(v2);

	}

}
