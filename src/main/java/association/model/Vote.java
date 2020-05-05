package association.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String accept;

	@ManyToOne(fetch = FetchType.EAGER)
	private Announcement announcement;

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

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
		if (announcement != null && !announcement.getVotes().contains(this)) {
			announcement.getVotes().add(this);
		}
	}

}
