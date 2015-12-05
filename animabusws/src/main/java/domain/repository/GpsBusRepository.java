package domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entity.GpsBus;
import infrastructure.persistence.jpa.GpsBusDAOJPA;

@Repository
public class GpsBusRepository {

	@Autowired
	private GpsBusDAOJPA dao;

	public void insert(GpsBus entity) {
		dao.create(entity);
	}

	public GpsBus get(long id) {
		return dao.read(id);
	}

	public List<GpsBus> listAll() {
		return dao.readAll();
	}

	public void save(GpsBus entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}
}
