package association.web.dto;

public class FlatDTO {

	private Long id;
	private String address;
	private String president;
	private Integer noOfApartments;
	private Integer noOfTenants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public Integer getNoOfApartments() {
		return noOfApartments;
	}

	public void setNoOfApartments(Integer noOfApartments) {
		this.noOfApartments = noOfApartments;
	}

	public Integer getNoOfTenants() {
		return noOfTenants;
	}

	public void setNoOfTenants(Integer noOfTenants) {
		this.noOfTenants = noOfTenants;
	}

}
