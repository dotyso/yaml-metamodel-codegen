package com.thsoft.metamodel.domain.feature;

import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.domain.Model;

public abstract class Feature extends MetaModel {
	private String name;
	private Model model;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	abstract public void render();
}
