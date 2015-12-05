package persistence;

import java.util.List;

import domain.entity.GpsBus;

public interface IGpsBusDAO {

	void create(GpsBus entity);

	GpsBus read(Long id);

	List<GpsBus> readAll();

	void update(GpsBus entity);

	void delete(Long id);
	
}
