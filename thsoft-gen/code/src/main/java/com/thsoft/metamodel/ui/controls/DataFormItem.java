package com.thsoft.metamodel.ui.controls;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import com.thsoft.metamodel.ui.databinding.DataBinding;

public class DataFormItem extends Control {

	private String type;
	private String text;
	private String label;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

	@Override
	public String getRenderTemplate() {
		return this.getType().toLowerCase() + ".html";
	}
	
	public Control getControl() {
		Control control = null;
		try {
			control = (Control)Class.forName("com.thsoft.metamodel.ui.controls." + this.getType()).newInstance();
			BeanUtils.copyProperties(control, this);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return control;
	}
	
	@Override
	public String render() {
		Control control = getControl();
        
		return this.getPage().getEngine().render(control.getRenderTemplate(), control);		
		
	}

	
}
