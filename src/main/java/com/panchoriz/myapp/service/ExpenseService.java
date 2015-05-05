package com.panchoriz.myapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panchoriz.myapp.model.ExpenseDTO;
import com.panchoriz.myapp.repositories.ExpenseRepository;
import com.panchoriz.myapp.repositories.documents.ExpenseDocument;
import com.panchoriz.myapp.utils.DateTimeUtil;

@Service("expenseService")
public class ExpenseService {
	
	// TODO: Create a Translator with Builders
	@Autowired private ExpenseRepository expenseRepository;

	public void saveExpense(ExpenseDocument expense) {
		expenseRepository.save(expense);
	}
	
	public Iterable<ExpenseDocument> getExpenses() {
		return expenseRepository.findAll();
	}
	
	public List<ExpenseDTO> getExpenseByDate(Date date, String owner) {
		List<ExpenseDTO> expenseModelList = new ArrayList<ExpenseDTO>();
		Iterable<ExpenseDocument> expenses = expenseRepository.findByDateAndOwner(date, owner);
		for(ExpenseDocument expenseDoc:expenses) {
			ExpenseDTO eModel = new ExpenseDTO();
			eModel.setExpenseId(expenseDoc.getDocumentId());
			eModel.setAmount(expenseDoc.getAmount());
			eModel.setComments(expenseDoc.getComments());
			eModel.setDescription(expenseDoc.getDescription());
			eModel.setType(expenseDoc.getType());
			expenseModelList.add(eModel);
		}
		return expenseModelList;
	}

	public void addNewExpense(ExpenseDTO expense, String owner) {
		// TODO: Need to create a builder class for Documents
		ExpenseDocument expenseDoc = new ExpenseDocument();
		expenseDoc.setOwner(owner);
		expenseDoc.setDate(DateTimeUtil.getTodaysDate());
		expenseDoc.setDocumentId(expenseDoc.getOwner() + 
				"_" + DateTimeUtil.getStringTodaysDateWithTime());
		save(expense, expenseDoc);
	}
	
	private void save(ExpenseDTO expense, ExpenseDocument expenseDoc) {
		expenseDoc.setAmount(expense.getAmount());
		expenseDoc.setComments(expense.getComments());
		expenseDoc.setType(expense.getType());
		expenseDoc.setDescription(expense.getDescription());
		expenseRepository.save(expenseDoc);
	}
	
	public void update(ExpenseDTO expense) {
		ExpenseDocument expenseDoc = expenseRepository.findByDocumentId(expense.getExpenseId());
		if(expenseDoc != null) {
			save(expense, expenseDoc);
		} else {
			// Throw error here
		}
	}
}
