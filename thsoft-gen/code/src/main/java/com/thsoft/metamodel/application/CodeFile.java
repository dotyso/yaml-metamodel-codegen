package com.thsoft.metamodel.application;

import com.thsoft.metamodel.application.Module;

public class CodeFile {

    private Module module;
    private String content;
    private String fileName;
    
    public CodeFile(Module module, String fileName, String content) {
        this.module = module;
        this.fileName = fileName;
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Module getModule() {
        return module;
    }
    public void setModule(Module module) {
        this.module = module;
    }

}
