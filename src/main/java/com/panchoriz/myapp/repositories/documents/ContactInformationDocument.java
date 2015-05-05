package com.panchoriz.myapp.repositories.documents;

import java.math.BigInteger;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contactInformation")
@TypeAlias("contactInfo")
public class ContactInformationDocument extends AbstractDocument<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7955169239134513195L;
	
	private String name;
	private String telNo;
	private String email;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
