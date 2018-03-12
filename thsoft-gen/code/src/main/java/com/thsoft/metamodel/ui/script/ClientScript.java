package com.thsoft.metamodel.ui.script;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.thsoft.metamodel.ui.Page;

public class ClientScript {

    private Page page;
    private Map includeMap = new HashMap<String, String>();
    private Map scriptBlockMap = new HashMap<String, Object>();
    private Map startupScriptBlockMap = new HashMap<String, ScriptBlock>();
    private Map declareScriptBlockMap = new HashMap<String, ScriptBlock>();
    
  
    public void registerDeclareScriptBlock(String key, ScriptBlock scriptBlock) {
    	declareScriptBlockMap.put(key, scriptBlock);
    }
    
    public void registerStartupScriptBlock(String key, ScriptBlock scriptBlock) {
    	startupScriptBlockMap.put(key, scriptBlock);
    }
    
    public void registerInclude(String key, String js) {
        includeMap.put(key, js);
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String renderInclude() {
        StringBuilder sb = new StringBuilder();
        for (Object key : includeMap.keySet()) {
            
            Map args = new LinkedHashMap();
            args.put("includeFile", includeMap.get(key));
            
            String result = this.getPage().getEngine().render("js/clientScriptInclude.html", args);
            sb.append(result);
        }
        return sb.toString();
    }
    
    public String renderDeclareBlock() {
        StringBuilder sb = new StringBuilder();
        
        Object[] keyArray = declareScriptBlockMap.keySet().toArray();     
        Arrays.sort(keyArray);     
        for (Object key : keyArray) {
        	ScriptBlock scriptBlock = (ScriptBlock)declareScriptBlockMap.get(key);
        	
            Map args = new LinkedHashMap();
            args.put("clientScript", this);
            args.put("scriptBlock", scriptBlock);
            if (scriptBlock.getTemplateArgs() != null) {
                args.putAll(scriptBlock.getTemplateArgs());
            }
            
            String result = this.getPage().getEngine().render(scriptBlock.getRenderTemplate(), args);
            sb.append(result);
        }
        return sb.toString();
    }

    public String renderStartupBlock() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n$(function () {");
        for (Object key : startupScriptBlockMap.keySet()) {
        	ScriptBlock scriptBlock = (ScriptBlock)startupScriptBlockMap.get(key);
        	
            Map args = new LinkedHashMap();
            args.put("clientScript", this);
            args.put("scriptBlock", scriptBlock);
            if (scriptBlock.getTemplateArgs() != null) {
                args.putAll(scriptBlock.getTemplateArgs());
            }
            
            String result = this.getPage().getEngine().render(scriptBlock.getRenderTemplate(), args);
            sb.append(result);
        }
        sb.append("\r\n});");
        return sb.toString();
    }
    
    public String renderRegisteredScriptBlock() {
        StringBuilder sb = new StringBuilder();
        for (Object key : scriptBlockMap.keySet()) {
        	ScriptBlock scriptBlock = (ScriptBlock)startupScriptBlockMap.get(key);
        	
            Map args = new HashMap();
            args.put("clientScript", this);
            
            String result = this.getPage().getEngine().render(scriptBlock.getRenderTemplate(), args);
            sb.append(result);
        }
        return sb.toString();
    }
}
