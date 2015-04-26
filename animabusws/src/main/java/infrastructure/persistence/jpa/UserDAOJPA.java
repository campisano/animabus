package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import persistence.IUserDAO;
import domain.entity.User;

@Component
public class UserDAOJPA extends GenericDAOJPA<User> implements IUserDAO {

	@Override
	public User read(String id) {
		return read(User.class, id);
	}

	@Override
	public List<User> readAll() {
		return readAll(User.class);
	}

	@Override
	public void delete(String id) {
		delete(User.class, id);
	}
}