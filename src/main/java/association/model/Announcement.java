package association.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Announcement {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false, unique = true)
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	private Flat flat;

	@OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vote> votes = new ArrayList<>();

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

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
		if (flat != null && !flat.getAnnouncements().contains(this)) {
			flat.getAnnouncements().add(this);
		}
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	
	public void addVote(Vote vote) {
		this.votes.add(vote);

		if (!this.equals(vote.getAnnouncement())) {
			vote.setAnnouncement(this);
		}
	}

}
