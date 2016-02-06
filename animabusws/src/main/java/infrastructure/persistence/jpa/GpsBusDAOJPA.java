package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.entity.GpsBus;
import persistence.IGpsBusDAO;

@Component
public class GpsBusDAOJPA extends GenericDAOJPA<GpsBus> implements
	IGpsBusDAO {

	@Override
	public GpsBus read(Long id) {
		return read(GpsBus.class, id);
	}

	@Override
	public List<GpsBus> readAll() {
		return readAll(GpsBus.class);
	}

	@Override
	public void delete(Long id) {
		delete(GpsBus.class, id);
	}

	@Transactional
	public void insertList(List<GpsBus> gpsOnibusList) {
		for(GpsBus bus: gpsOnibusList)
		{
			try {
				entityManager.persist(bus);
			} catch (Exception ex) {
				logger.warning(ex.toString());
				throw new DAOException("Entity create error.", ex);
			}
		}
	}
}
