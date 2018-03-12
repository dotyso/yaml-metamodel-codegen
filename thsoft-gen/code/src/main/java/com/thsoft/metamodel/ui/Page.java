package com.thsoft.metamodel.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.abego.treelayout.internal.util.java.lang.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.rythmengine.RythmEngine;
import com.thsoft.gen.templateengine.TemplateEngine;
import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.application.Module;
import com.thsoft.metamodel.ui.action.Action;
import com.thsoft.metamodel.ui.action.DataSourceAction;
import com.thsoft.metamodel.ui.controls.Control;
import com.thsoft.metamodel.ui.databinding.DataBinding;
import com.thsoft.metamodel.ui.enums.PageGenerateMode;
import com.thsoft.metamodel.ui.script.ClientScript;
import com.thsoft.metamodel.ui.script.VueAjaxScriptBlock;
import com.thsoft.metamodel.ui.script.VueData;
import com.thsoft.metamodel.ui.script.VueDeclareScriptBlock;

public class Page extends MetaModel {

    private String currentGenerateMode;
    private String generateMode = PageGenerateMode.Custom;
    private List<String> generateModes;
    private String name;
	private String title;
	private String dataContext;
	private List<Control> controls = new ArrayList<Control>();
	private List<Action> actions;
	private List<DataSource> dataSources = new ArrayList<DataSource>();
	private TemplateEngine engine;
	private ClientScript clientScript;
	private Module module;
	
	public Page() {
		clientScript = new ClientScript();
		clientScript.setPage(this);
		generateModes = new ArrayList<String>();
		generateModes.add(PageGenerateMode.Custom);
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
    
	public void createChildControls() {
    
	    if (this.getControls() != null) {
    		for (Control control : this.getControls()) {
    			control.setPage(this);
    			control.createChildControls();
    		}
		}
		
	    //对于生成模式为Update, Detail的直接生成DataSource
        if (this.getCurrentGenerateMode().equals(PageGenerateMode.Update) || this.getCurrentGenerateMode().equals(PageGenerateMode.Detail)) {
            if (StringUtils.isNoneBlank(this.getName()) && StringUtils.isNoneBlank(this.getDataContext())) {
                DataSource dataSource = new DataSource();
                dataSource.setId(this.getDataContext());
                dataSource.setName(this.getDataContext());
                dataSource.setMethod(new DataSourceAction(this.getModule(), this.getDataContext()).getMethod());
                
                this.addOrUpdateDataSources(dataSource);
            }
        }
        else if (this.getCurrentGenerateMode().equals(PageGenerateMode.Insert)) {
            
        }
	    
		//根据DataSource注册声明和启动脚本
	    if (this.getDataSources().size() != 0) {
    		List<VueData> vueData = new ArrayList<VueData>();
    		
    		//数据源
    		for (DataSource dataSource : this.getDataSources()) {
    			vueData.add(new VueData(dataSource.getName(), dataSource.getDefaultValue()));
    			
    			if (StringUtils.isNoneBlank(dataSource.getMethod())) {
    			    this.clientScript.registerStartupScriptBlock(dataSource.getName(), new VueAjaxScriptBlock(dataSource.getName(), dataSource.getMethod()));
    			}
    		}
    	    
    		this.clientScript.registerDeclareScriptBlock("_vueDeclare", new VueDeclareScriptBlock(vueData));
	    }

	}
	
	private void addOrUpdateDataSources(DataSource dataSource) {
	    for (DataSource currentDataSource : this.getDataSources()) {
	        if (currentDataSource.getName().equals(dataSource.getName())) {
	            currentDataSource = dataSource;
	            return;
	        }
	    }
	    
	    this.getDataSources().add(dataSource);        
    }

    public String render() {
		return this.getEngine().render(this.getRenderTemplate(), this, this.clientScript);
	}

	public String renderChildControls() {
	    
	    if (this.getControls().size() != 0) {
    		StringBuilder sb = new StringBuilder();
    		
    		for (Control control : this.getControls()) {
    		    control.preRender();
    			String result = control.render();
    			sb.append(result);
    		}
    
    		return sb.toString();
	    }

	    return "";
	}	

	@Override
	public String getRenderTemplate() {
		return "ui/" + this.getClass().getSimpleName().toLowerCase() + ".html";
	}

	public List<DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<DataSource> dataSources) {
		this.dataSources = dataSources;
	}
	
	public Action getAction(String actionId) {
	    
	    for (Action action : actions) {
	        if (action.getId().equals(actionId)) {
	            return action;
	        }
	    }
	        
	    return null;
	}

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
    
    public String getGenerateMode() {
        return generateMode;
    }

    public void setGenerateMode(String generateMode) {
        this.generateMode = generateMode;
    }

    public String getCurrentGenerateMode() {
        return currentGenerateMode;
    }

    public void setCurrentGenerateMode(String currentGenerateMode) {
        this.currentGenerateMode = currentGenerateMode;
    }

    public List<String> getGenerateModes() {
        this.generateModes = new ArrayList<String>(Arrays.asList(this.getGenerateMode().split(", ")));
        return generateModes;
    }

}
