package com.panchoriz.myapp.views;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.ObjectError;

import com.panchoriz.myapp.model.BaseDTO;

public abstract class BaseView<T extends BaseDTO> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7040964107633354217L;
	public abstract String getViewName();
	
	private T formModel;
	public T getFormModel() {
		return formModel;
	};
	public void setFormModel(T formModel) {
		this.formModel = formModel;
	};
	
	// TODO: Create List of errors here :) (Own version converted from ObjectError)
	private List<ObjectError> errors;
	public List<ObjectError> getErrors() {
		return errors;
	}
	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}
	
}
