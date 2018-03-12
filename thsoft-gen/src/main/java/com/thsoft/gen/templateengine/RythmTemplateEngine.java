package com.thsoft.gen.templateengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.rythmengine.RythmEngine;

public class RythmTemplateEngine extends TemplateEngine {

    private RythmEngine engine;
    public RythmTemplateEngine() {
        
        Map<String, Object> conf = new HashMap<String, Object>();
        conf.put("home.template", "template");
        conf.put("rythm.codegen.compact", "false");
            
        engine = new RythmEngine(conf);
    }

    @Override
    public String render(String template, Map args) {
        
        Collection<?> valueList = args.values();
        List<?> list = new ArrayList(valueList);
        Object[] objects = list.toArray();
        
        return engine.render(template, objects);
        
    }
}
