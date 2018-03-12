package com.thsoft.metamodel.ui.action;

public class ActionListener {
    
    private Action action;
    private String refId;
    
    public String getRefId() {
        return refId;
    }
    public void setRefId(String refId) {
        this.refId = refId;
    }
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    
    @Override
    public String toString() {
        return this.getAction().getMethod();
    }
}
