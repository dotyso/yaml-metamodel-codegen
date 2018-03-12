package com.thsoft.gen.templateengine;

import java.util.Map;

public abstract class TemplateEngine {

    abstract public String render(String template, Object...args);
    abstract public String render(String template, Map args);
    
}
