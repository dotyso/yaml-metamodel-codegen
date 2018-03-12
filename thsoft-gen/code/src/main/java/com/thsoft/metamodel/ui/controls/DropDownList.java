package com.thsoft.metamodel.ui.controls;

import java.util.HashMap;
import java.util.Map;
import com.thsoft.metamodel.ui.validator.Validatable;
import com.thsoft.metamodel.ui.validator.ValidatorList;

public class DropDownList extends ListControl implements Validatable {

	
	private ValidatorList validators;

	public ValidatorList getValidators() {
		return validators;
	}

	public void setValidators(ValidatorList validators) {
		this.validators = validators;
	}
	
	@Override
	public String render() {
      
		String result = this.getPage().getEngine().render(this.getRenderTemplate(), this);	
		return result;
	}
}
