package com.panchoriz.myapp.constants;

public enum ExpenseTypeEnum {
	
	FIXED_EXPENSE(1, "FIXED EXPENSE"), VARIABLE_EXPENSE(2, "VARIABLE EXPENSE"), NON_ESSENTIAL_EXPENSE(3, "NON-ESSENTIAL");
	
	private int index;
	private String expenseName;
	
	private ExpenseTypeEnum(int index, String expenseName) {
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
	
	public static ExpenseTypeEnum getExpense(int index) {
		switch(index){
		case 1: return FIXED_EXPENSE;
		case 2: return VARIABLE_EXPENSE;
		case 3: return NON_ESSENTIAL_EXPENSE;
		default: return null;
		}
	}
}
