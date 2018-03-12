package com.thsoft.metamodel.ui.validator;

import java.util.ArrayList;

public class ValidatorList extends ArrayList<Validator> {

	private boolean required;
	private String regex;
	
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
		if (required)
			this.add(new RequiredValidator());
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
		this.add(new RegexValidator(regex));
	}

}
