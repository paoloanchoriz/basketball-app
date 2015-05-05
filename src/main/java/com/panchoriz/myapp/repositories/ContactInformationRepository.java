package com.panchoriz.myapp.repositories;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panchoriz.myapp.repositories.documents.ContactInformationDocument;

@Repository
public interface ContactInformationRepository extends CrudRepository<ContactInformationDocument, BigInteger> {

}
