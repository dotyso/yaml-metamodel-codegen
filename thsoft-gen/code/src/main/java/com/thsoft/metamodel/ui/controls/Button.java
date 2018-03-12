package com.thsoft.metamodel.ui.controls;

import com.thsoft.metamodel.ui.annotation.WebControlAttribute;

public class Button extends Control {

    @WebControlAttribute(name="value")
	private String text;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
