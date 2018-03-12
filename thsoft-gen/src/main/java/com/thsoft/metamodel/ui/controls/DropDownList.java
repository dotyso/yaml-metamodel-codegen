package com.thsoft.metamodel.ui.controls;

import java.util.HashMap;
import java.util.Map;
import com.thsoft.metamodel.ui.validator.ValidatorList;

public class DropDownList extends ListControl {

	
	private ValidatorList validators;

	public ValidatorList getValidators() {
		return validators;
	}

	public void setValidators(ValidatorList validators) {
		this.validators = validators;
	}
	
	@Override
	public String render() {
	    
        Map args = new HashMap();
        args.put("control", this);
        
		String result = this.getPage().getEngine().render(this.getRenderTemplate(), args);	
		return result;
	}
}
