package domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private Date timestamp;

	@Column(nullable = false)
	private double heading;

	@Column(nullable = false)
	private double altitude;

	@Column(nullable = false)
	private double latitude;

	@Column(nullable = false)
	private double longitude;

	@Column(nullable = false)
	private double accuracy;

	@Column(nullable = false)
	private double altitudeAccuracy;

	@Column(nullable = false)
	private double speed;

	@ManyToOne(optional = false)
	private Track track;

	protected Position() {
	}

	public Position(Date timestamp, double heading, double altitude,
			double latitude, double longitude, double accuracy,
			double altitudeAccuracy, double speed, Track track) {
		this();

		if (timestamp == null) {
			throw new IllegalArgumentException("Timestamp invalid.");
		}

		if (accuracy < 0) {
			throw new IllegalArgumentException("Accuracy invalid.");
		}

		if (altitudeAccuracy < 0) {
			throw new IllegalArgumentException("AltitudeAccuracy invalid.");
		}

		if (speed < 0) {
			throw new IllegalArgumentException("Speed invalid.");
		}

		if (track == null) {
			throw new IllegalArgumentException("Track can not be null.");
		}

		this.timestamp = timestamp;
		this.heading = heading;
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.altitudeAccuracy = altitudeAccuracy;
		this.speed = speed;
		this.track = track;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public long getId() {
		return id;
	}

	public double getHeading() {
		return heading;
	}

	public double getAltitude() {
		return altitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public double getAltitudeAccuracy() {
		return altitudeAccuracy;
	}

	public double getSpeed() {
		return speed;
	}

	public Track getTrack() {
		return track;
	}
}
