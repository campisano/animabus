package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import domain.entity.GpsBus;
import persistence.IGpsBusDAO;

@Component
public class GpsBusDAOJPA extends GenericDAOJPA<GpsBus> implements
	IGpsBusDAO {

	public GpsBus read(Long id) {
		return read(GpsBus.class, id);
	}

	public List<GpsBus> readAll() {
		return readAll(GpsBus.class);
	}

	public void delete(Long id) {
		delete(GpsBus.class, id);
	}
}
