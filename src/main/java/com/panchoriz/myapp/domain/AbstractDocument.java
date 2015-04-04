package com.panchoriz.myapp.domain;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AbstractDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4584817533161133677L;
	
	@Id private BigInteger documentId;

	public BigInteger getDocumentId() {
		return documentId;
	}

	public void setDocumentId(BigInteger documentId) {
		this.documentId = documentId;
	}
	
	
}
