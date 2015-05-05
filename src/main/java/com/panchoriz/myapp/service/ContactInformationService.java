package com.panchoriz.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panchoriz.myapp.repositories.ContactInformationRepository;
import com.panchoriz.myapp.repositories.documents.ContactInformationDocument;

@Service("contactInformationService")
public class ContactInformationService {

	@Autowired
	private ContactInformationRepository contactInformationRepository;

	public ContactInformationDocument getContactInformation() {
		Iterable<ContactInformationDocument> iterable = contactInformationRepository.findAll();
		ContactInformationDocument contactInformation = null;
		if(iterable != null && iterable.iterator() != null && iterable.iterator().hasNext()) {
			contactInformation = iterable.iterator().next();		
		} else {
			contactInformation = new ContactInformationDocument();
			contactInformation.setName("Paolo Alejandro Anchoriz");
			contactInformation.setEmail("paolo.anchoriz@gmail.com");
			contactInformation.setTelNo("09175961429");
			contactInformation.setAddress("T3 U3519 SMDC Light Residences, Mandaluyong City");
			contactInformationRepository.save(contactInformation);
		}
		return contactInformation;
	}
}
