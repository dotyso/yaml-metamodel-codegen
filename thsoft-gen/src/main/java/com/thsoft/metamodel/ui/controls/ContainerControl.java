package com.thsoft.metamodel.ui.controls;

import java.util.List;

import com.thsoft.metamodel.ui.databinding.DataBinding;

public abstract class ContainerControl extends Control {

	private List<Control> controls;

	public void setControls(List<Control> controls) {
		this.controls = controls;
	}
	
	public List<Control> getControls() {
		return this.controls;
	}

	public String renderChildControls() {

		StringBuilder sb = new StringBuilder();
		for (Control control : this.getControls()) {
		
			String result = control.render();
			sb.append(result);
		}

		return sb.toString();
	}

	@Override
	public void init() {

		super.init();
		
		for (Control control : this.getControls()) {
			control.setPage(this.getPage());
			control.init();
		}
	}
}
