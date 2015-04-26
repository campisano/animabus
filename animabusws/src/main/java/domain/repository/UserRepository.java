package domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persistence.IUserDAO;
import domain.entity.User;

@Component
public class UserRepository {

	@Autowired
	private IUserDAO dao;

	public void insert(User entity) {
		dao.create(entity);
	}

	public User get(String id) {
		return dao.read(id);
	}

	public List<User> listAll() {
		return dao.readAll();
	}

	public void save(User entity) {
		dao.update(entity);
	}

	public void remove(String id) {
		dao.delete(id);
	}
}
