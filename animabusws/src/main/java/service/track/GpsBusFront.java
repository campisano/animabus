package service.track;

import java.util.Date;

public class GpsBusFront {

	private Date timestamp;
	private Double heading;
	private Double altitude;
	private Double latitude;
	private Double longitude;
	private Double accuracy;
	private Double altitude_accuracy;
	private Double speed;

	public GpsBusFront() {
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	public Double getAltitude_accuracy() {
		return altitude_accuracy;
	}

	public void setAltitude_accuracy(Double altitude_accuracy) {
		this.altitude_accuracy = altitude_accuracy;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}
}