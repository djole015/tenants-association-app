package association.web.dto;

public class VoteDTO {

	private Long id;
	private String accept;

	private Long announcementId;
	private String announcementTitle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

}
