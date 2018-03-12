package com.thsoft.metamodel.application;

import java.util.List;
import java.util.Set;

public class ContextConfig {

	private String outputPath;
	private String webappOutputPath;
    private List<String> yamlBeanPackages;
	
	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

    public String getWebappOutputPath() {
        return webappOutputPath;
    }

    public void setWebappOutputPath(String webappOutputPath) {
        this.webappOutputPath = webappOutputPath;
    }

    public List<String> getYamlBeanPackages() {
        return yamlBeanPackages;
    }

    public void setYamlBeanPackages(List<String> yamlBeanPackages) {
        this.yamlBeanPackages = yamlBeanPackages;
    }


}
