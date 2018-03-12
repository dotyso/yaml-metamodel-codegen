package com.thsoft.metamodel.domain;

import com.thsoft.metamodel.MetaModel;

public class Property extends MetaModel {
	private String name;
	private String type;
	private String label;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
