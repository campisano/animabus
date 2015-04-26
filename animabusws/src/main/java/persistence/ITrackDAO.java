package persistence;

import java.util.List;

import domain.entity.Track;

public interface ITrackDAO {

	void create(Track entity);

	Track read(Long id);

	List<Track> readAll();

	void update(Track entity);

	void delete(Long id);
}
