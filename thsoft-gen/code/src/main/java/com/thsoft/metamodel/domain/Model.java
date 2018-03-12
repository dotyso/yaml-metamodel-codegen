package com.thsoft.metamodel.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.application.Module;
import com.thsoft.metamodel.domain.feature.Feature;

public class Model extends MetaModel {

	private String name;
	private List<Property> properties;
	private List<Feature> features;
	private Module module;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public String getCamelName() {
        return StringUtils.uncapitalize(name);
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	public void createChildren() {
	       
        for (Feature feature : features) {
            feature.setEngine(this.getEngine());
            feature.setModel(this);
        }
	}
	
	public String render() {

        Map args = new HashMap();
        args.put("model", this);
		
		return this.getEngine().render(this.getRenderTemplate(), args);
	}

	

}
