package com.panchoriz.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panchoriz.myapp.domain.Expense;
import com.panchoriz.myapp.service.ExpenseService;
import com.panchoriz.myapp.utils.DateTimeUtil;
import com.panchoriz.myapp.validators.ExpenseValidator;

@Controller
@RequestMapping("/expense")
public class ExpenseController extends AbstractSaveController<Expense> {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ExpenseValidator expenseValidator;
	
	@RequestMapping
	public String expense(Model model) {
		model.addAttribute("expenseView", "expenseView");
		model.addAttribute("expenseTypes", Expense.ExpenseEnum.values());
		model.addAttribute("expenseList", expenseService.getExpenses());
		return "expense";
	}
	
	@Override
	void save(Expense expense) {
		expense.setOwner("panchoriz");
		expense.setDate(DateTimeUtil.getTodaysDate());
		expenseService.saveExpense(expense);
	}

	@Override
	Validator getValidator() {
		return expenseValidator;
	}

	@Override
	String getRedirectPage() {
		return "expense";
	}

	@Override
	String getDocumentName() {
		return "expense";
	}
	
}

