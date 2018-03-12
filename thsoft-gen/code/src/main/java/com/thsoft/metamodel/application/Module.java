package com.thsoft.metamodel.application;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.thsoft.metamodel.domain.Model;
import com.thsoft.metamodel.ui.Page;

public class Module {
	
	private String id;
	private String title;
	private String name;
	private String packageName;
	private List<Page> pages = new ArrayList<Page>();
	private List<Model> models = new ArrayList<Model>();
	private Application application;
	private String modulePath;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public Application getApplication() {
        return application;
    }
    public void setApplication(Application application) {
        this.application = application;
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

	public String getModulePath() {
		return modulePath;
	}
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
    public String getJavaProjectName() {
        return this.getApplication().getCompanyCodeName().toLowerCase() + "-" + this.getApplication().getName().toLowerCase() + "-" + this.getName().toLowerCase();
    }
    public String getPackageName() {
        if (StringUtils.isBlank(packageName)) {
            packageName = this.getName();
        }
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
