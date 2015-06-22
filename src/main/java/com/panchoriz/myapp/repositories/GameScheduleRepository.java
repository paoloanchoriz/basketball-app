package com.panchoriz.myapp.repositories;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.panchoriz.myapp.repositories.documents.GameScheduleDocument;

public interface GameScheduleRepository extends PagingAndSortingRepository<GameScheduleDocument, String>, QueryDslPredicateExecutor<GameScheduleDocument> {

}
