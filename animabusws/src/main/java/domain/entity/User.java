package domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	private String id;

	private User() {
	}

	public User(String id) {
		this();

		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("ID can not be empty.");
		}

		this.id = id;
	}

	public String getId() {
		return id;
	}
}
