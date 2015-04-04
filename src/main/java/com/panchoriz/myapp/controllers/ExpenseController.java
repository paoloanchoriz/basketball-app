package com.panchoriz.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.panchoriz.myapp.domain.Expense;
import com.panchoriz.myapp.service.ExpenseService;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping
	public String expense(Model model) {
		model.addAttribute("expenseView", "expenseView");
		model.addAttribute("expenseList", expenseService.getExpenses());
		return "expense";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveExpense(Expense expense, Model model) {
		// TODO: validation	
		// TODO: fill this will all the expenses available
		expenseService.saveExpense(expense);
		return "redirect:/expense";
	}
	
}

