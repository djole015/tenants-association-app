package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Announcement;
import association.web.dto.AnnouncementDTO;

@Component
public class AnnouncementToAnnouncementDTO implements Converter<Announcement, AnnouncementDTO> {

	@Override
	public AnnouncementDTO convert(association.model.Announcement source) {
		AnnouncementDTO dto = new AnnouncementDTO();

		dto.setId(source.getId());
		dto.setTitle(source.getTitle());
		dto.setDescription(source.getDescription());
		dto.setAccepted(source.isAccepted());
		dto.setType(source.getType());
		dto.setPercentageNeeded(source.getPercentageNeeded());

		dto.setFlatId(source.getFlat().getId());
		dto.setFlatAddress(source.getFlat().getAddress());

		return dto;
	}

	public List<AnnouncementDTO> convert(List<Announcement> sourceList) {
		List<AnnouncementDTO> dtoList = new ArrayList<>();

		for (Announcement source : sourceList) {
			dtoList.add(convert(source));
		}

		return dtoList;
	}

}
