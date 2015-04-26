package domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persistence.IMovimentDAO;
import domain.entity.Moviment;

@Component
public class MovimentRepository {

	@Autowired
	private IMovimentDAO dao;

	public void insert(Moviment entity) {
		dao.create(entity);
	}

	public Moviment get(long id) {
		return dao.read(id);
	}

	public List<Moviment> listAll() {
		return dao.readAll();
	}

	public void save(Moviment entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}
}
