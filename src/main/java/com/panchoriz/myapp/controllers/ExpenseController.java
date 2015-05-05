package com.panchoriz.myapp.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panchoriz.myapp.constants.ExpenseTypeEnum;
import com.panchoriz.myapp.model.ExpenseDTO;
import com.panchoriz.myapp.service.ExpenseService;
import com.panchoriz.myapp.utils.DateTimeUtil;
import com.panchoriz.myapp.validators.ExpenseValidator;
import com.panchoriz.myapp.views.ExpenseView;

@Controller
@RequestMapping("/expense")
public class ExpenseController extends AbstractController<ExpenseDTO, ExpenseView>  {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ExpenseValidator expenseValidator;
	
	@Override
	protected ExpenseView buildView() {
		ExpenseTypeEnum.values();
		ExpenseView view = new ExpenseView();
		Date todaysDate = DateTimeUtil.getTodaysDate();
		List<ExpenseDTO> expenses = expenseService.getExpenseByDate(todaysDate, "panchoriz"); 
		view.setExpenseList(expenses);
		BigDecimal totalExpense = BigDecimal.ZERO;
		for(ExpenseDTO expense:expenses) {
			totalExpense = totalExpense.add(expense.getAmount());
		}
		view.setTotalAmount(totalExpense);
		view.setDateOfExpense(DateTimeUtil.getStringTodaysDate());
		return view;
	}
	
	@Override
	protected void save(ExpenseDTO expense, BindingResult result) {
		// TODO: Owner will be replaced by the session's user name
		if(StringUtils.isBlank(expense.getExpenseId())) {
			expenseService.addNewExpense(expense, "panchoriz");
		} else {
			expenseService.update(expense);
		}
	}

	@Override
	protected Validator getValidator() {
		return expenseValidator;
	}

	@Override
	protected String getRedirectPage() {
		return "expense";
	}
	
}

