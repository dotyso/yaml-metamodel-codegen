package com.thsoft.metamodel.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.thsoft.metamodel.ui.databinding.DataBinding;

public abstract class ContainerControl extends Control {

	private List<Control> controls = new ArrayList<Control>();

	public void setControls(List<Control> controls) {
		this.controls = controls;
	}
	
	public List<Control> getControls() {
		return this.controls;
	}

    @Override
    public void createChildControls() {

        super.createChildControls();
        
        if (this.getControls() != null) {
            for (Control control : this.getControls()) {
                control.setPage(this.getPage());
                control.createChildControls();
            }
        }
    }

	public String renderChildControls() {

	    if (this.getControls().size() != 0) {
    		StringBuilder sb = new StringBuilder();
    		for (Control control : this.getControls()) {
    		    control.preRender();
    			String result = control.render();
    			sb.append(result);
    		}
    		
    	      return sb.toString();
	    }

	    return "";

	}

}
