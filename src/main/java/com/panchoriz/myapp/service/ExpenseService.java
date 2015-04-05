package com.panchoriz.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panchoriz.myapp.domain.Expense;
import com.panchoriz.myapp.repositories.ExpenseRepository;

@Service("expenseService")
public class ExpenseService {

	@Autowired private ExpenseRepository expenseRepository;

	public void saveExpense(Expense expense) {
		expenseRepository.save(expense);
	}
	
	public Iterable<Expense> getExpenses() {
		return expenseRepository.findAll();
	}
	
}
