package association;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import association.model.Flat;
import association.model.Message;
import association.service.FlatService;
import association.service.MessageService;

@Component
public class TestData {
	@Autowired
	private MessageService messageService;

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
		f3.setNoOfApartments(1);
		f3.setNoOfTenants(3);

		flatService.save(f3);

		Message a1 = new Message();
		a1.setTitle("finance");
		a1.setDescription("empty fund");
		a1.setType("notice");
		a1.setFlat(f1);

		messageService.save(a1);

		Message a2 = new Message();
		a2.setTitle("lock replacement");
		a2.setDescription("6/15 locksmith comming");
		a2.setType("notice");
		a2.setFlat(f1);

		messageService.save(a2);

		Message a3 = new Message();
		a3.setTitle("no water/low pressure");
		a3.setDescription("6/11 from 8am-10am");
		a3.setType("notice");
		a3.setFlat(f2);

		messageService.save(a3);

		Message a4 = new Message();
		a4.setTitle("facade painting");
		a4.setDescription("in favor or against");
		a4.setType("proposal");
		a4.setPercentageNeeded(66D);
		a4.setFlat(f3);

		messageService.save(a4);

	}

}
