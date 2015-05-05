package com.panchoriz.myapp.repositories.documents;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="expenses")
@TypeAlias("expense")
public class ExpenseDocument extends AbstractDocument<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5077022451775837688L;
	
	private String description;
	private String owner;
	private BigDecimal amount;
	private Date date;
	private String comments;
	private int type;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
