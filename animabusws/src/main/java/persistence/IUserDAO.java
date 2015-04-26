package persistence;

import java.util.List;

import domain.entity.User;

public interface IUserDAO {

	void create(User entity);

	User read(String id);

	List<User> readAll();

	void update(User entity);

	void delete(String id);
}
