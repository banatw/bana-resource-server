package org.bana.model.json;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonUser {
	private String username;
	private String address;
	@JsonProperty("dokumen")
	private Set<JsonDocument> documents;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<JsonDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<JsonDocument> documents) {
		this.documents = documents;
	}
	public JsonUser(String username, String address, Set<JsonDocument> documents) {
		super();
		this.username = username;
		this.address = address;
		this.documents = documents;
	}

	
}
