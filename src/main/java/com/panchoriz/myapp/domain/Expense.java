package com.panchoriz.myapp.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.panchoriz.myapp.utils.DateTimeUtil;

@Document(collection="expenses")
@TypeAlias("expense")
public class Expense extends AbstractDocument {
	
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
	public String getDate() {
		return DateTimeUtil.getFormattedDate(date);
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
	public String getType() {
		ExpenseEnum expenseType = ExpenseEnum.getExpense(this.type);
		return expenseType != null ? expenseType.getExpenseName():null;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	public static enum ExpenseEnum {
		
		FIXED_EXPENSE(1, "FIXED EXPENSE"), VARIABLE_EXPENSE(2, "VARIABLE EXPENSE"), NON_ESSENTIAL_EXPENSE(3, "NON-ESSENTIAL");
		
		private int index;
		private String expenseName;
		
		private ExpenseEnum(int index, String expenseName) {
			this.index = index;
			this.expenseName = expenseName;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getExpenseName() {
			return expenseName;
		}

		public void setExpenseName(String expenseName) {
			this.expenseName = expenseName;
		}
		
		public static ExpenseEnum getExpense(int index) {
			switch(index){
			case 1: return FIXED_EXPENSE;
			case 2: return VARIABLE_EXPENSE;
			case 3: return NON_ESSENTIAL_EXPENSE;
			default: return null;
			}
		}
	}
}
