package com.thsoft.metamodel;

import org.rythmengine.RythmEngine;
import com.thsoft.gen.templateengine.TemplateEngine;

public class MetaModel {
	
	private String id;
	private TemplateEngine engine;
	
	public String getRenderTemplate() {
		return this.getClass().getSimpleName().toLowerCase() + ".html";
	}

	public TemplateEngine getEngine() {
		return engine;
	}

	public void setEngine(TemplateEngine engine) {
		this.engine = engine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
