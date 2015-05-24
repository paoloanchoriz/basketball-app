package com.panchoriz.myapp.repositories;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.panchoriz.myapp.repositories.documents.VenueDocument;

@Repository
public interface VenueRepository extends PagingAndSortingRepository<VenueDocument, String>, QueryDslPredicateExecutor<VenueDocument> {

}
