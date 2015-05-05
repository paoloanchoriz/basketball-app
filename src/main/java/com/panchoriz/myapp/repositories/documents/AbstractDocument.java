package com.panchoriz.myapp.repositories.documents;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class AbstractDocument<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4584817533161133677L;
	
	@Id private T documentId;

	public T getDocumentId() {
		return documentId;
	}

	public void setDocumentId(T documentId) {
		this.documentId = documentId;
	}
	
	
}
