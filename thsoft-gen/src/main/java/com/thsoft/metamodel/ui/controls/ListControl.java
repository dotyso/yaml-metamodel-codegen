package com.thsoft.metamodel.ui.controls;

public abstract class ListControl extends Control {

	private String itemSource;
	private String itemValueField;
	private String itemTextField;
	private String label;
	
	public String getItemSource() {
		return itemSource;
	}
	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}
	public String getItemValueField() {
		return itemValueField;
	}
	public void setItemValueField(String itemValueField) {
		this.itemValueField = itemValueField;
	}
	public String getItemTextField() {
		return itemTextField;
	}
	public void setItemTextField(String itemTextField) {
		this.itemTextField = itemTextField;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
