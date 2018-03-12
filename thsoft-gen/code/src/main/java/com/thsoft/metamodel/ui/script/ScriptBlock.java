package com.thsoft.metamodel.ui.script;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.thsoft.gen.ApplicationGenerator;

public class ScriptBlock {

    private String template = "js/" + this.getClass().getSimpleName().toLowerCase() + ".js";
    private Map templateArgs;
    
    public ScriptBlock() {

    }
    
    public ScriptBlock(String template) {
        this.template = template;
    }
    
    public ScriptBlock(String template, Map templateArgs) {
        this.template = template;
        this.templateArgs = templateArgs;
    }
    
	public String getRenderTemplate() {
		return template;
	}
	
	public void setRenderTemplate(String template) {
	    this.template = template;
	}
	
    public Map getTemplateArgs() {
        return templateArgs;
    }
	
	public String render() {
	    
        Map args = new LinkedHashMap();
        args.put("scriptBlock", this);
        if (templateArgs != null) {
            args.putAll(templateArgs);
        }
        
		return ApplicationGenerator.getInstance().getEngine().render(this.getRenderTemplate(), args);		
	}


}
