package com.thsoft.metamodel.ui.script;

import java.util.HashMap;
import java.util.Map;
import com.thsoft.gen.ApplicationGenerator;

public class ScriptBlock {

	public String getRenderTemplate() {
		return "js/" + this.getClass().getSimpleName().toLowerCase() + ".js";
	}
	
	public String render() {
	    
        Map args = new HashMap();
        args.put("scriptBlock", this);
        
		return ApplicationGenerator.getEngine().render(this.getRenderTemplate(), args);		
	}
}
