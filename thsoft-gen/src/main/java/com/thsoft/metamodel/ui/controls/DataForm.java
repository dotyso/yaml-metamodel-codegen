package com.thsoft.metamodel.ui.controls;

import java.util.List;

public class DataForm extends Control {

	private List<DataFormItem> items;
	private String dataContext;
	
	public String getDataContext() {
		return dataContext;
	}

	public void setDataContext(String dataContext) {
		this.dataContext = dataContext;
	}
	
	public List<DataFormItem> getItems() {
		return items;
	}

	public void setItems(List<DataFormItem> items) {
		this.items = items;
	}

	public String renderChildControls() {
		
		StringBuilder sb = new StringBuilder();
		for (DataFormItem control : this.getItems()) {
			control.setPage(this.getPage());
			if (this.getDataContext() != null) {
				control.setDataContext(this.getDataContext());
			}
			if (control.getDataBinding() != null) {
				control.getDataBinding().setSource(this.getDataContext());
			}
			
			String result = control.render();
			//String result = this.getPage().getEngine().render(control.getRenderTemplate(), control.getControl());
			sb.append(result);
		}
		
		return sb.toString();

	}
}
