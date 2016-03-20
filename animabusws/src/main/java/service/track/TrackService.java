package service.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entity.Track;
import domain.entity.Position;
import domain.entity.Moviment;
import domain.entity.User;
import domain.repository.MovimentRepository;
import domain.repository.TrackRepository;
import domain.repository.PositionRepository;
import domain.repository.UserRepository;

@Component
public class TrackService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TrackRepository trackRepository;

	@Autowired
	private MovimentRepository movimentRepository;

	@Autowired
	private PositionRepository positionRepository;

	public TrackStoreResponse storeTrack(TrackStoreRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getImei() == null || request.getImei().trim().equals("")) {
			throw new IllegalArgumentException("Undefined IMEI identifier.");
		}

		if (request.getLine_number() == null
				|| request.getLine_number().trim().equals("")) {
			throw new IllegalArgumentException(
					"Undefined line_number identifier.");
		}

		if (request.getVote() == null) {
			throw new IllegalArgumentException("Undefined vote.");
		}

		if (request.getStart_time() == null) {
			throw new IllegalArgumentException("Undefined start_time.");
		}

		if (request.getEnd_time() == null) {
			throw new IllegalArgumentException("Undefined end_time.");
		}

		User user = userRepository.get(request.getImei());

		if (user == null) {
			user = new User(request.getImei());
			userRepository.insert(user);
		}

		Track track = new Track(request.getLine_number(), request.getVote(),
				request.getStart_time(), request.getEnd_time(), user);
		trackRepository.insert(track);

		for (GpsBusFront gps : request.getGps()) {
			if (gps.getTimestamp() != null) {
				double heading = gps.getHeading() == null ? 0 : gps
						.getHeading().doubleValue();
				double altitude = gps.getAltitude() == null ? 0 : gps
						.getAltitude().doubleValue();
				double latitude = gps.getLatitude() == null ? 0 : gps
						.getLatitude().doubleValue();

				double longitude = gps.getLongitude() == null ? 0 : gps
						.getLongitude().doubleValue();
				double accuracy = gps.getAccuracy() == null ? 0 : gps
						.getAccuracy().doubleValue();
				double altitudeAccuracy = gps.getAltitude_accuracy() == null ? 0
						: gps.getAltitude_accuracy().doubleValue();

				double speed = gps.getSpeed() == null ? 0 : gps.getSpeed()
						.doubleValue();

				Position position = new Position(gps.getTimestamp(), heading,
						altitude, latitude, longitude, accuracy,
						altitudeAccuracy, speed, track);
				positionRepository.insert(position);
			}
		}

		for (AccelerometerRequest accelerometer : request.getAccelerometer()) {
			if (accelerometer.getTimestamp() != null) {
				double x = accelerometer.getX() == null ? 0 : accelerometer
						.getX().doubleValue();
				double y = accelerometer.getY() == null ? 0 : accelerometer
						.getY().doubleValue();
				double z = accelerometer.getZ() == null ? 0 : accelerometer
						.getZ().doubleValue();

				Moviment moviment = new Moviment(accelerometer.getTimestamp(),
						x, y, z, track);
				movimentRepository.insert(moviment);
			}
		}

		return new TrackStoreResponse("Track stored successfully.");
	}
}
