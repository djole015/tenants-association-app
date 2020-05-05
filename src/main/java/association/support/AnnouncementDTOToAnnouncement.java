package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Announcement;
import association.model.Flat;
import association.service.AnnouncementService;
import association.service.FlatService;
import association.web.dto.AnnouncementDTO;

@Component
public class AnnouncementDTOToAnnouncement implements Converter<AnnouncementDTO, Announcement> {

	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private FlatService stanjeService;

	@Override
	public Announcement convert(AnnouncementDTO dto) {
		Flat flat = stanjeService.findOne(dto.getFlatId());

		if (flat != null) {
			Announcement announcement = null;

			if (dto.getId() != null) {
				announcement = announcementService.findOne(dto.getId());
			} else {
				announcement = new Announcement();
			}

			announcement.setTitle(dto.getTitle());
			announcement.setDescription(dto.getDescription());
			announcement.setType(dto.getType());
			announcement.setAccepted(dto.isAccepted());
			announcement.setPercentageNeeded(dto.getPercentageNeeded());

			announcement.setFlat(flat);

			return announcement;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Announcement> convert(List<AnnouncementDTO> dtoList) {
		List<Announcement> ret = new ArrayList<>();

		for (AnnouncementDTO dto : dtoList) {
			ret.add(convert(dto));
		}

		return ret;
	}
}
