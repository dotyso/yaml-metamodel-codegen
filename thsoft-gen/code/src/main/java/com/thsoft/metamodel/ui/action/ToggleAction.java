package com.thsoft.metamodel.ui.action;

public class ToggleAction extends VueAction {
    public String getMethod() {
        return "!" + this.getArgs();
    }
}
