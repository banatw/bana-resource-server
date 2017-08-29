package org.bana.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="m_user")
public class User {
	@Id
	private String username;
	private String password;
	private String address;
	@OneToMany(mappedBy="user")
	private Set<Document> documents;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	public User(String username, String password, String address, Set<Document> documents) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.documents = documents;
	}
	public User() {
		super();
	}

}
