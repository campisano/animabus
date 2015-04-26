package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import persistence.IPositionDAO;
import domain.entity.Position;

@Component
public class PositionDAOJPA extends GenericDAOJPA<Position> implements
		IPositionDAO {

	@Override
	public Position read(Long id) {
		return read(Position.class, id);
	}

	@Override
	public List<Position> readAll() {
		return readAll(Position.class);
	}

	@Override
	public void delete(Long id) {
		delete(Position.class, id);
	}
}