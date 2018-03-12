package com.thsoft.metamodel.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.rythmengine.RythmEngine;
import com.thsoft.gen.templateengine.TemplateEngine;
import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.application.Module;
import com.thsoft.metamodel.ui.controls.Control;
import com.thsoft.metamodel.ui.script.ClientScript;
import com.thsoft.metamodel.ui.script.VueAjaxScriptBlock;
import com.thsoft.metamodel.ui.script.VueDeclareScriptBlock;

public class Page extends MetaModel {

	private String name;
	private String title;
	private String dataContext;
	private List<Control> controls;
	private List<DataSource> dataSources;
	private TemplateEngine engine;
	private ClientScript clientScript;
	private Module module;
	private String outputFilePath;
	private String outputPath;
	
	public Page() {
		clientScript = new ClientScript();
		clientScript.setPage(this);
	}
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	public String getDataContext() {
		return dataContext;
	}

	public void setDataContext(String dataContext) {
		this.dataContext = dataContext;
	}
	
	public ClientScript getClientScript() {
		return clientScript;
	}

	public void setClientScript(ClientScript clientScript) {
		this.clientScript = clientScript;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Control> getControls() {
		return this.controls;
	}
	
	public void setControls(List<Control> controls) {
		this.controls = controls;
	}
	
	@Override
	public TemplateEngine getEngine() {

		return engine;
	}
	
	@Override
	public void setEngine(TemplateEngine engine) {
		this.engine = engine;
		
		if (this.controls != null) {
    		for (Control control : this.controls) {
    			control.setPage(this);
    		}
		}
	}
	
	public void init() {
		
	    if (this.getControls() != null) {
    		for (Control control : this.getControls()) {
    			control.setPage(this);
    			control.init();
    		}
		}
		
		//根据DataSource注册声明和启动脚本
	    if (dataSources != null) {
    		List<String> vueDate = new ArrayList<String>();
    		for (DataSource dataSource : this.dataSources) {
    			vueDate.add(dataSource.getName());
    			this.clientScript.registerStartupScriptBlock(dataSource.getName(), new VueAjaxScriptBlock(dataSource.getName(), dataSource.getMethod()));
    		}
    	      this.clientScript.registerDeclareScriptBlock("vueDeclare", new VueDeclareScriptBlock(vueDate));
	    }

	}
	
	public String render() {
	    Map args = new LinkedHashMap();
	    args.put("page", this);
	    args.put("clientScript", this.clientScript);
	    
		return this.getEngine().render(this.getRenderTemplate(), args);
	}

	public String renderChildControls() {
	    
	    if (this.getControls() == null)
	        return "";

		StringBuilder sb = new StringBuilder();
		
		for (Control control : this.getControls()) {
			String result = control.render();
			sb.append(result);
		}

		return sb.toString();
	}	

	@Override
	public String getRenderTemplate() {
		return "ui/" + this.getClass().getSimpleName().toLowerCase() + ".html";
	}
	
	public String getOutputPath() {
		if (StringUtils.isBlank(outputPath)) {
			outputPath = module.getApplication().getConfig().getOutput() + "/WEB-INF/views/" + module.getApplication().getCodeName() + "/" + module.getCodeName();
			
		}
		return outputPath;
	}
	
	public String getOutputFilePath() {
		if (StringUtils.isBlank(outputFilePath)) {
			outputFilePath = getOutputPath() + "/" + this.getName() + ".html";
			
		}
		return outputFilePath;
	}
	
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public List<DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<DataSource> dataSources) {
		this.dataSources = dataSources;
	}
}
