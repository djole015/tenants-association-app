package association.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import association.model.Announcement;
import association.service.AnnouncementService;
import association.support.AnnouncementDTOToAnnouncement;
import association.support.AnnouncementToAnnouncementDTO;
import association.web.dto.AnnouncementDTO;

@RestController
@RequestMapping(value = "/api/announcements")
public class ApiAnnouncementController {
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private AnnouncementToAnnouncementDTO toDTO;
	
	@Autowired
	AnnouncementDTOToAnnouncement toAnnouncement;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AnnouncementDTO>> getAnnouncements(
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum
			) {
	/*
	 * @RequestParam(required = false) String ime,
	 * 
	 * @RequestParam(required = false) Long flatId,
	 * 
	 * 
	 */

		Page<Announcement> announcementsPage;

		/*
		 * if (ime != null || sprintId != null ) { announcementiPage =
		 * announcementService.search(ime, sprintId, pageNum); } else { }
		 */
		announcementsPage = announcementService.findAll(pageNum);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(announcementsPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(announcementsPage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable Long id) {
		Announcement announcement = announcementService.findOne(id);
		if (announcement == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(announcement), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AnnouncementDTO> delete(@PathVariable Long id) {
		Announcement deleted = announcementService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AnnouncementDTO> add(@Validated @RequestBody AnnouncementDTO newAnnouncementDTO) {

		Announcement savedAnnouncement = announcementService.save(toAnnouncement.convert(newAnnouncementDTO));

		return new ResponseEntity<>(toDTO.convert(savedAnnouncement), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<AnnouncementDTO> edit(@Validated @RequestBody AnnouncementDTO announcementDTO, @PathVariable Long id) {

		if (!id.equals(announcementDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Announcement persisted = announcementService.save(toAnnouncement.convert(announcementDTO));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
