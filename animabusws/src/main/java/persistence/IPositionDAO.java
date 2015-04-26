package persistence;

import java.util.List;

import domain.entity.Position;

public interface IPositionDAO {

	void create(Position entity);

	Position read(Long id);

	List<Position> readAll();

	void update(Position entity);

	void delete(Long id);
}
