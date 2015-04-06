package com.panchoriz.myapp.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panchoriz.myapp.domain.AbstractDocument;

public abstract class AbstractSaveController<T extends AbstractDocument> {

	abstract Validator getValidator();
	abstract String getRedirectPage();
	abstract String getDocumentName();
	abstract void save(T document);
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public final String save(T document, BindingResult result, RedirectAttributes redirectAttributes) {
		getValidator().validate(document, result);
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
			redirectAttributes.addFlashAttribute(getDocumentName(), document);
		} else {
			save(document);
		}
		return "redirect:/" + getRedirectPage();
	}
}
