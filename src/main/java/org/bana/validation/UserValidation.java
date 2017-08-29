package org.bana.validation;

import org.hibernate.validator.constraints.NotEmpty;

public class UserValidation {
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;

	public UserValidation() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
