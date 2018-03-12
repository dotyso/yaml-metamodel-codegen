package com.thsoft.metamodel.ui.controls;

import java.util.List;

import com.thsoft.metamodel.ui.annotation.WebControlAttribute;
import com.thsoft.metamodel.ui.validator.Validatable;
import com.thsoft.metamodel.ui.validator.Validator;
import com.thsoft.metamodel.ui.validator.ValidatorList;

public class TextBox extends Control implements Validatable {
	
	@WebControlAttribute(name="value")
	private String text;
	
	private String label;
	
	private ValidatorList validators;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public ValidatorList getValidators() {
		return validators;
	}
	
	public void setValidators(ValidatorList validators) {
		this.validators = validators;
	}
}
