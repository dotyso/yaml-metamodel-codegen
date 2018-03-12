package com.thsoft.metamodel.domain.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.thsoft.metamodel.application.CodeFile;

public class CurdFeature extends Feature {



    @Override
	public void render() {
	    
        Map args = new LinkedHashMap();
        args.put("feature", this);
        args.put("model", this.getModel());
        
		String serviceContent = this.getEngine().render("feature/service.html", args);
		this.getCodeFiles().add(new CodeFile(this.getModel().getModule(), "service\\" + this.getModel().getName() + "Service.java", serviceContent));
		
		String repositoryContent = this.getEngine().render("feature/repository.html", args);
		this.getCodeFiles().add(new CodeFile(this.getModel().getModule(), "repository\\" + this.getModel().getName() + "Repository.java", repositoryContent));
	      
	    String controllerContent = (this.getEngine().render("feature/controller.html", args));
	    this.getCodeFiles().add(new CodeFile(this.getModel().getModule(), "controller\\" + this.getModel().getName() + "Controller.java", controllerContent));
	}
    


}
