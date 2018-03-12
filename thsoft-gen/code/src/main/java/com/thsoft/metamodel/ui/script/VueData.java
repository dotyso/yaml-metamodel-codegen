package com.thsoft.metamodel.ui.script;

public class VueData {

    private String name;
    private Object defaultValue;
    
    public VueData() {
        
    }
    
    public VueData(String name, Object defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
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
