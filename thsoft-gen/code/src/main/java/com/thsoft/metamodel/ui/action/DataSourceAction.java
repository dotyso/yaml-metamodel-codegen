package com.thsoft.metamodel.ui.action;

import com.thsoft.metamodel.application.Module;
import com.thsoft.metamodel.domain.Model;

public class DataSourceAction extends Action {
    public DataSourceAction(Module module, String modelName){
        this.setName(modelName);
        this.setMethod("/" + module.getApplication().getName() + "/" + module.getName() + "/findById");
    }
    

}
