package domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persistence.IPositionDAO;
import domain.entity.Position;

@Component
public class PositionRepository {

	@Autowired
	private IPositionDAO dao;

	public void insert(Position entity) {
		dao.create(entity);
	}

	public Position get(long id) {
		return dao.read(id);
	}

	public List<Position> listAll() {
		return dao.readAll();
	}

	public void save(Position entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}
}
