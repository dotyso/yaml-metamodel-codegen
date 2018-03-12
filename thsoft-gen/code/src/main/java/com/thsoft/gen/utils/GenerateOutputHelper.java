package com.thsoft.gen.utils;

import org.apache.commons.lang3.StringUtils;
import com.thsoft.metamodel.application.CodeFile;
import com.thsoft.metamodel.application.Module;
import com.thsoft.metamodel.domain.Model;
import com.thsoft.metamodel.ui.Page;
import com.thsoft.metamodel.ui.enums.PageGenerateMode;

public class GenerateOutputHelper {

    public static String getOutputPath(Model model) {

        String outputPath = getSourceCodeRootPath(model.getModule()) + "\\" + model.getModule().getApplication().getPackageName().replace(".", "\\") + "\\" + model.getModule().getPackageName().replace(".", "\\") + "\\model";

        return outputPath;
    }
        
    public static String getOutputFilePath(Model model) {
        String outputFilePath = getOutputPath(model) + "\\" + model.getName() + ".java";
        return outputFilePath;
    }
    
    
    public static String getOutputPath(Page page) {
        
        String webPageOutputPath = "";
        if (StringUtils.isNotBlank(page.getModule().getApplication().getWebappOutputPath())) {
            webPageOutputPath = page.getModule().getApplication().getWebappOutputPath();
        }
        else {
            webPageOutputPath = page.getModule().getApplication().getJavaProjectOutputPath();
        }
        
        String outputPath = webPageOutputPath + "\\" + page.getModule().getApplication().getWebProjectName() + "\\WEB-INF\\views\\" + page.getModule().getApplication().getName() + "\\" + page.getModule().getName();

        return outputPath;
    }
    
    public static String getOutputFilePath(Page page) {
        String outputFilePath = "";
        if (page.getCurrentGenerateMode().equals(PageGenerateMode.Custom))
            outputFilePath = getOutputPath(page) + "\\" + page.getName() + ".html";
        else
            outputFilePath = getOutputPath(page) + "\\" + page.getName() + page.getCurrentGenerateMode() + ".html";

        return outputFilePath;
    }
    
    
    public static String getSourceCodeRootPath(Module module) {
        String outpath = module.getApplication().getJavaProjectOutputPath() + "\\" + module.getJavaProjectName() + "\\src\\main\\java\\";
        return outpath;
    }

    public static String getOutputFilePath(CodeFile file) {
        String outputPath = getSourceCodeRootPath(file.getModule()) + "\\" + file.getModule().getApplication().getPackageName().replace(".", "\\") + "\\" + file.getModule().getPackageName().replace(".", "\\") + "\\" + file.getFileName();

        return outputPath;
    }
}
