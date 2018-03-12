package com.thsoft.metamodel.application;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.thsoft.metamodel.domain.Model;

public class Application {

    private String id;
    private String title;
    private String name;
    private String description;
    private String companyCodeName;
    private String webProjectName;
    private String packageName;
    private String javaProjectOutputPath;
    private String webappOutputPath;
    private String applicationPath;
    private List<Module> modules = new ArrayList<Module>();
    private ContextConfig config;

    public String getJavaProjectOutputPath() {
        return javaProjectOutputPath;
    }

    public void setJavaProjectOutputPath(String javaProjectOutputPath) {
        this.javaProjectOutputPath = javaProjectOutputPath;
    }

    public String getWebappOutputPath() {
        return webappOutputPath;
    }

    public void setWebappOutputPath(String webappOutputPath) {
        this.webappOutputPath = webappOutputPath;
    }
    
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
        if (StringUtils.isBlank(id))
            name = this.id;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public String getCompanyCodeName() {
        return companyCodeName;
    }

    public void setCompanyCodeName(String companyCodeName) {
        this.companyCodeName = companyCodeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getWebProjectName() {
        return webProjectName;
    }

    public void setWebProjectName(String webProjectName) {
        this.webProjectName = webProjectName;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    /*
    public ContextConfig getConfig() {
        return config;
    }

    public void setConfig(ContextConfig config) {
        this.config = config;
    }
    */
    
    public Model getModel(String modelId) {
        for (Module module : this.getModules()) {
            for (Model model : module.getModels()) {
                if (model.getName().toLowerCase().equals(modelId.toLowerCase()))
                    return model;
            }
        }
        return null;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }


}
