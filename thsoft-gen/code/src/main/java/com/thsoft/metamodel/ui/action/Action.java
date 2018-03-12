package com.thsoft.metamodel.ui.action;

import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
import com.thsoft.metamodel.ui.Page;

public class Action {
    private String id;
    private String name;
    private String method;
    private String args;

    public Action() {
        
    }
    
    public Action(String id, String name, String method) {
        this.id = id;
        this.name = name;
        this.method = method;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    @Override
    public String toString() {
        return this.getMethod();
    }

    public static Action createWithContext(Page page) {
        //自动跟据上下文，生成action
        String restUrl = page.getModule().getApplication().getName() + "/" + page.getDataContext() + "/" + page.getCurrentGenerateMode();
        return new Action(null, null, restUrl);
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

}
