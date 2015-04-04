package com.panchoriz.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panchoriz.myapp.service.ContactInformationService;

@Controller
@RequestMapping("/contact")
public class ContactInformationController {
	
	@Autowired
	ContactInformationService contactInformationService;
		
	@RequestMapping
	public String contact(Model model) {
		model.addAttribute("contactInformation", contactInformationService.getContactInformation());
		return "contact";
	}
}
