package person;

import java.io.Serializable;

public class RememberMe implements Serializable {
	private String username;
	private String password;

	public RememberMe(String username, String password) {
		this.username = username;
		this.password = password;

	}

	public void setUsername(String username) {
		this.username = username;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getUsername() {

		return username;
	}

	public String getPassword() {

		return password;
	}

}