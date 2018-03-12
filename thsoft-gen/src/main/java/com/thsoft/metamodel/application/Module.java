package com.thsoft.metamodel.application;

import java.util.ArrayList;
import java.util.List;

import com.thsoft.metamodel.domain.Model;
import com.thsoft.metamodel.ui.Page;

public class Module {
	
	private String id;
	private String name;
	private String codeName;
	private List<Page> pages = new ArrayList<Page>();
	private List<Model> models = new ArrayList<Model>();
	private Application application;
	private String modulePath;
	private String moduleAbsolutePath;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	
	
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public String getModulePath() {
		return modulePath;
	}
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
	public String getModuleAbsolutePath() {
		return moduleAbsolutePath;
	}
	public void setModuleAbsolutePath(String moduleAbsolutePath) {
		this.moduleAbsolutePath = moduleAbsolutePath;
	}

}
