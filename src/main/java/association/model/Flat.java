package association.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Flat {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String address;

	@OneToMany(mappedBy = "flat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Announcement> announcements = new ArrayList<>();

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

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	public void addAnnouncement(Announcement announcement) {
		this.announcements.add(announcement);

		if (!this.equals(announcement.getFlat())) {
			announcement.setFlat(this);
		}
	}
}
