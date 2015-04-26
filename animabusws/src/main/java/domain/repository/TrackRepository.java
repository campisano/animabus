package domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persistence.ITrackDAO;
import domain.entity.Track;

@Component
public class TrackRepository {

	@Autowired
	private ITrackDAO dao;

	public void insert(Track entity) {
		dao.create(entity);
	}

	public Track get(long id) {
		return dao.read(id);
	}

	public List<Track> listAll() {
		return dao.readAll();
	}

	public void save(Track entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}
}
