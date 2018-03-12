package com.thsoft.metamodel.ui.validator;

public class RegexValidator extends Validator {

	private String regex;
	
	public RegexValidator() {
		
	}
	
	public RegexValidator(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

}
