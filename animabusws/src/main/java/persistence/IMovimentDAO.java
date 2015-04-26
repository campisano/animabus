package persistence;

import java.util.List;

import domain.entity.Moviment;

public interface IMovimentDAO {

	void create(Moviment entity);

	Moviment read(Long id);

	List<Moviment> readAll();

	void update(Moviment entity);

	void delete(Long id);
}
