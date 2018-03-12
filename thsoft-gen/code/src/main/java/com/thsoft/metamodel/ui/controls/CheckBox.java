package com.thsoft.metamodel.ui.controls;

import com.thsoft.metamodel.ui.action.ActionListener;
import com.thsoft.metamodel.ui.annotation.WebControlAttribute;
import com.thsoft.metamodel.ui.validator.Validatable;
import com.thsoft.metamodel.ui.validator.ValidatorList;

public class CheckBox extends Control  {

	private String text;
	private String label;
	
	@WebControlAttribute(name="v-on:click")
    private ActionListener onClick;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
    public ActionListener getOnClick() {
        return onClick;
    }
    public void setOnClick(ActionListener onClick) {
        this.onClick = onClick;
    }
	
}
