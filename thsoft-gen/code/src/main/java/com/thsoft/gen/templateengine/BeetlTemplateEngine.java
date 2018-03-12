package com.thsoft.gen.templateengine;

import java.io.IOException;
import java.util.Map;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

public class BeetlTemplateEngine extends TemplateEngine {

    private GroupTemplate gt;
    
    public BeetlTemplateEngine() throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        
    }
    
    public String render(String template, Map args) {

        Template t = gt.getTemplate("template/" + template);
        t.binding(args);
        return t.render();
    }

    @Override
    public String render(String template, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

}
