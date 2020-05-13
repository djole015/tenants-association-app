package association.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class AnnouncementDTO {

	private Long id;
	@NotEmpty
	private String title;
	private String type;
	@Min(0)
	@Max(100)
	private Double percentageNeeded;
	private boolean accepted = false;
	private String description;

	private Long flatId;
	private String flatAddress;
	private Integer flatNoOfTenants;

	private Integer noOfVotes;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPercentageNeeded() {
		return percentageNeeded;
	}

	public void setPercentageNeeded(Double percentageNeeded) {
		this.percentageNeeded = percentageNeeded;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getFlatNoOfTenants() {
		return flatNoOfTenants;
	}

	public void setFlatNoOfTenants(Integer flatNoOfTenants) {
		this.flatNoOfTenants = flatNoOfTenants;
	}

	public Integer getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(Integer noOfVotes) {
		this.noOfVotes = noOfVotes;
	}

}
