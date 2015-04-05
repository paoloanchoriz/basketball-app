package com.panchoriz.myapp.validators;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.panchoriz.myapp.domain.Expense;

@Component("expenseValidator")
public class ExpenseValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Expense.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Expense expense = (Expense) target;
		
		if(StringUtils.isBlank(expense.getDescription())) {
			errors.rejectValue("description", "", "Please provide a proper description.");
		}
		
		if(expense.getAmount() == null || expense.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue("amount", "", "Please provide an amount greater than zero.");
		}
		
		
		if(StringUtils.isBlank(expense.getType())) {
			errors.rejectValue("type", "", "Please provide a valid expense type.");
		}
	}

}
