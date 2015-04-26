package service.track;

import java.util.Date;
import java.util.List;

public class TrackStoreRequest {

	private String imei;
	private String line_number;
	private Date start_time;
	private Date end_time;
	private Integer vote;
	private List<GpsRequest> gps;
	private List<AccelerometerRequest> accelerometer;

	public TrackStoreRequest() {
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getLine_number() {
		return line_number;
	}

	public void setLine_number(String line_number) {
		this.line_number = line_number;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public List<GpsRequest> getGps() {
		return gps;
	}

	public void setGps(List<GpsRequest> gps) {
		this.gps = gps;
	}

	public List<AccelerometerRequest> getAccelerometer() {
		return accelerometer;
	}

	public void setAccelerometer(List<AccelerometerRequest> accelerometer) {
		this.accelerometer = accelerometer;
	}
}
