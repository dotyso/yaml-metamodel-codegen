package com.thsoft.metamodel.domain.feature;

import java.util.ArrayList;
import java.util.List;
import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.application.CodeFile;
import com.thsoft.metamodel.domain.Model;

public abstract class Feature extends MetaModel {
	private String name;
	private Model model;

    private List<CodeFile> codeFiles = new ArrayList<CodeFile>();
    
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
	
    
    public List<CodeFile> getCodeFiles() {
        return codeFiles;
    }

    public void setFeatureFiles(List<CodeFile> codeFiles) {
        this.codeFiles = codeFiles;
    }
}
