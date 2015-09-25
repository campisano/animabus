package domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "moviment")
public class Moviment {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private Date timestamp;

	@Column(nullable = false)
	private double x;

	@Column(nullable = false)
	private double y;

	@Column(nullable = false)
	private double z;

	@ManyToOne(optional = false)
	private Track track;

	protected Moviment() {
	}

	public Moviment(Date timestamp, double x, double y, double z, Track track) {
		this();

		if (timestamp == null) {
			throw new IllegalArgumentException("Timestamp invalid.");
		}

		if (track == null) {
			throw new IllegalArgumentException("Track can not be null.");
		}

		this.timestamp = timestamp;
		this.x = x;
		this.y = y;
		this.z = z;
		this.track = track;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public long getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Track getTrack() {
		return track;
	}
}
