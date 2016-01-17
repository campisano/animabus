package domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abws_user")
public class User {

	@Id
	private String id;

	protected User() {
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
