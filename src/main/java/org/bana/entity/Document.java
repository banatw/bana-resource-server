package org.bana.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Document {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idDocument;
	
	private String documentName;
	
	@ManyToOne
	private User user;

	public Integer getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Integer idDocument) {
		this.idDocument = idDocument;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public User getDataPerson() {
		return user;
	}

	public void setDataPerson(User dataPerson) {
		this.user = dataPerson;
	}

	public Document(String documentName, User dataPerson) {
		super();
		this.documentName = documentName;
		this.user = dataPerson;
	}

	public Document() {
		super();
	}

	public Document(Integer idDocument, String documentName, User user) {
		super();
		this.idDocument = idDocument;
		this.documentName = documentName;
		this.user = user;
	}

}
