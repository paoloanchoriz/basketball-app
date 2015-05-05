package com.panchoriz.myapp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panchoriz.myapp.repositories.documents.ExpenseDocument;

@Repository
public interface ExpenseRepository extends CrudRepository<ExpenseDocument, String> {
	
	List<ExpenseDocument> findByDateAndOwner(Date date, String owner);
	
	ExpenseDocument findByDocumentId(String Id);
	
}
