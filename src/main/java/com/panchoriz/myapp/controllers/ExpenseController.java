package com.panchoriz.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panchoriz.myapp.domain.Expense;
import com.panchoriz.myapp.service.ExpenseService;
import com.panchoriz.myapp.utils.DateTimeUtil;
import com.panchoriz.myapp.validators.ExpenseValidator;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
	
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
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveExpense(Expense expense, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		// TODO: validation
		expenseValidator.validate(expense, result);
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
			redirectAttributes.addFlashAttribute("expense", expense);
		} else {
			// Temporarily set this to owner - panchoriz until Users and ACL are fully
			// implemented.
			expense.setOwner("panchoriz");
			expense.setDate(DateTimeUtil.getTodaysDate());
			expenseService.saveExpense(expense);
		}
		
		return "redirect:/expense";
	}
	
}

