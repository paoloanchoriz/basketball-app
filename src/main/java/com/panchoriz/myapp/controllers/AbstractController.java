package com.panchoriz.myapp.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panchoriz.myapp.model.BaseDTO;
import com.panchoriz.myapp.views.BaseView;

public abstract class AbstractController<T extends BaseDTO, V extends BaseView<T>> {

	protected abstract Validator getValidator();
	protected abstract String getRedirectPage();
	protected abstract void save(T formModel, BindingResult result);
	protected abstract V buildView();
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(T document, BindingResult result, RedirectAttributes redirectAttributes) {
		getValidator().validate(document, result);
		
		if(isSuccessful(result, document, redirectAttributes)) {
			save(document, result);
			// Call again to check if save is successful
			isSuccessful(result, document, redirectAttributes);
		}
		
		return "redirect:/" + getRedirectPage();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/save/j", method=RequestMethod.POST)
	public V getRESTResult(T document, BindingResult result, RedirectAttributes redirectAttributes) {
		save(document, result, redirectAttributes);
		V view = getView(redirectAttributes.asMap());
		return view;
	}

	@RequestMapping
	public String getTemplate(Model model) {
		Map<String, Object> modelMap = model.asMap();
		V view = getView(modelMap);
		modelMap.put("view", view);
		return view.getViewName();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/j", method=RequestMethod.GET)
	public V getRESTView(Model model) {
		return getView(model.asMap());
	}
	
	private boolean isSuccessful(BindingResult result, T document, RedirectAttributes redirectAttributes) {
		boolean isSuccessful = !result.hasErrors();
		if(!isSuccessful) {
			redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
			redirectAttributes.addFlashAttribute("formModel", document);
		}
		return isSuccessful;
	}
	
	@SuppressWarnings("unchecked")
	private V getView(Map<String, Object> modelMap) {
		V view = buildView();
		if(modelMap.get("errors") != null) {
			view.setFormModel((T) modelMap.get("formModel"));
		}
		return view;
	}
	
}
