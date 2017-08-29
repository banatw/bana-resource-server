package org.bana.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(alphabetic=true)
public class JsonDocument {
	@JsonProperty("id_dokumen")
	private Integer idDocument;	
	
	@JsonProperty("nama_dokumen")
	private String documentName;
	
	public JsonDocument() {
		// TODO Auto-generated constructor stub
	}

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

	public JsonDocument(Integer idDocument, String documentName) {
		super();
		this.idDocument = idDocument;
		this.documentName = documentName;
	}

}
