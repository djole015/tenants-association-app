package association.web.dto;

public class AnnouncementDTO {

	private Long id;
	private String title;

	private Long flatId;
	private String flatAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getFlatId() {
		return flatId;
	}

	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}

	public String getFlatAddress() {
		return flatAddress;
	}

	public void setFlatAddress(String flatAddress) {
		this.flatAddress = flatAddress;
	}

}
