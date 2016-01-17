package domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "abws_track")
public class Track {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String lineNumber;

	@Column(nullable = false)
	private int vote;

	@Column(nullable = false)
	private Date startTime;

	@Column(nullable = false)
	private Date endTime;

	@ManyToOne(optional = false)
	private User user;

	protected Track() {
	}

	public Track(String lineNumber, int vote, Date startTime, Date endTime,
			User user) {
		this();

		if (lineNumber == null || lineNumber.equals("")) {
			throw new IllegalArgumentException(
					"Line number code can not be empty.");
		}

		if (vote < 0 || vote > 5) {
			throw new IllegalArgumentException("Vote invalid.");
		}

		if (user == null) {
			throw new IllegalArgumentException("User can not be null.");
		}

		this.lineNumber = lineNumber;
		this.vote = vote;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public int getVote() {
		return vote;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public User getUser() {
		return user;
	}
}
