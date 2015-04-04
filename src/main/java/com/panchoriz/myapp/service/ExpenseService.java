package com.panchoriz.myapp.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panchoriz.myapp.domain.Expense;
import com.panchoriz.myapp.repositories.ExpenseRepository;

@Service("expenseService")
public class ExpenseService {

	@Autowired private ExpenseRepository expenseRepository;
	
	// TODO: Create a DateUtil for date manipulation
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMMyyyy");
	
	public void saveExpense(Expense expense) {
		expense.setDateString(DATE_FORMAT.format(Calendar.getInstance().getTime()));
		expenseRepository.save(expense);
	}
	
	public Iterable<Expense> getExpenses() {
		return expenseRepository.findAll();
	}
	
}
