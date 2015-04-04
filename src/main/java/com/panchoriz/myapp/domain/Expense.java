package com.panchoriz.myapp.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="expenses")
@TypeAlias("expense")
public class Expense extends AbstractDocument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5077022451775837688L;
	
	private String description;
	private BigDecimal amount;
	@Field("date")
	private String dateString;
	private String comments;
	private String type;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
