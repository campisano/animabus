package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import persistence.IMovimentDAO;
import domain.entity.Moviment;

@Component
public class MovimentDAOJPA extends GenericDAOJPA<Moviment> implements
		IMovimentDAO {

	@Override
	public Moviment read(Long id) {
		return read(Moviment.class, id);
	}

	@Override
	public List<Moviment> readAll() {
		return readAll(Moviment.class);
	}

	@Override
	public void delete(Long id) {
		delete(Moviment.class, id);
	}
}