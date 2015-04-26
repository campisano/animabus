package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import persistence.ITrackDAO;
import domain.entity.Track;

@Component
public class TrackDAOJPA extends GenericDAOJPA<Track> implements ITrackDAO {

	@Override
	public Track read(Long id) {
		return read(Track.class, id);
	}

	@Override
	public List<Track> readAll() {
		return readAll(Track.class);
	}

	@Override
	public void delete(Long id) {
		delete(Track.class, id);
	}
}