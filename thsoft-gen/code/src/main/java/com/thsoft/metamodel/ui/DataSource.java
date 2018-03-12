package com.thsoft.metamodel.ui;

public class DataSource {
	private String id;
	private String name;
	private String method;
	private Object defaultValue;
	
	public DataSource() {}
	public DataSource(String id, String name, Object defaultValue) {
	    this.id = id;
	    this.name = name;
	    this.defaultValue = defaultValue;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public Object getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
