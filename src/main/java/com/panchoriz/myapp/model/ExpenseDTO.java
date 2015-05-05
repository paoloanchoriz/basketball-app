package com.panchoriz.myapp.model;

import java.math.BigDecimal;

import com.panchoriz.myapp.constants.ExpenseTypeEnum;

public class ExpenseDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6155921223214960400L;
	
	private String expenseId;
	private String description;
	private BigDecimal amount;
	private String comments;
	private int type;
	private String typeDescription;
	
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
		ExpenseTypeEnum expenseType = ExpenseTypeEnum.getExpense(type);
		if(expenseType == null) {
			expenseType = ExpenseTypeEnum.NON_ESSENTIAL_EXPENSE;
		}
		this.typeDescription = expenseType.getExpenseName();
		this.type = type;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
}
