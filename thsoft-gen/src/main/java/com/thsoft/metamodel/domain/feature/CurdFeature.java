package com.thsoft.metamodel.domain.feature;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurdFeature extends Feature {

	@Override
	public void render() {
	    
        Map args = new LinkedHashMap();
        args.put("feature", this);
        args.put("model", this.getModel());
        
		System.out.println(this.getEngine().render("feature/service.html", args));
		System.out.println(this.getEngine().render("feature/repository.html", args));
		System.out.println(this.getEngine().render("feature/controller.html", args));
	}

}
