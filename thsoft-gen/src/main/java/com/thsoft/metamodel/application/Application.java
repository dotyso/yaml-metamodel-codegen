package com.thsoft.metamodel.application;

import java.util.ArrayList;
import java.util.List;

public class Application {

	private String id;
	private String name;
	private String codeName;
	private String applicationPath;
	private String applicationAbsolutePath;
	private List<Module> modules = new ArrayList<Module>();
	private GenerateConfig config;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationPath() {
		return applicationPath;
	}
	public void setApplicationPath(String applicationPath) {
		this.applicationPath = applicationPath;
	}
	public String getApplicationAbsolutePath() {
		return applicationAbsolutePath;
	}
	public void setApplicationAbsolutePath(String applicationAbsolutePath) {
		this.applicationAbsolutePath = applicationAbsolutePath;
	}
	public GenerateConfig getConfig() {
		return config;
	}
	public void setConfig(GenerateConfig config) {
		this.config = config;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

}
