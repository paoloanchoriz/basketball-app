package com.panchoriz.myapp.views;

import java.math.BigDecimal;
import java.util.List;

import com.panchoriz.myapp.constants.ExpenseTypeEnum;
import com.panchoriz.myapp.model.ExpenseDTO;

public class ExpenseView extends BaseView<ExpenseDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275308124592156451L;
	private String dateOfExpense;
	private BigDecimal totalAmount;
	private List<ExpenseDTO> expenseList;
	
	public ExpenseTypeEnum[] getExpenseTypes() {
		return ExpenseTypeEnum.values();
	}
	public String getDateOfExpense() {
		return dateOfExpense;
	}
	public void setDateOfExpense(String dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<ExpenseDTO> getExpenseList() {
		return expenseList;
	}
	public void setExpenseList(List<ExpenseDTO> expenseList) {
		this.expenseList = expenseList;
	}
	public String getViewName() {
		return "expense";
	}

}
