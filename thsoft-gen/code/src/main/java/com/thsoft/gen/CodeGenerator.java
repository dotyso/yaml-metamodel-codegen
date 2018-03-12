package com.thsoft.gen;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.cglib.beans.BeanCopier;
import com.thsoft.gen.templateengine.TemplateEngine;
import com.thsoft.gen.utils.GenerateOutputHelper;
import com.thsoft.metamodel.application.CodeFile;
import com.thsoft.metamodel.domain.Model;
import com.thsoft.metamodel.domain.feature.Feature;
import com.thsoft.metamodel.ui.Page;
import com.thsoft.metamodel.ui.enums.PageGenerateMode;

public class CodeGenerator {

    private TemplateEngine engine;
    
    public CodeGenerator(TemplateEngine engine) {
        this.engine = engine;
    }
    
    public void generate(Page page) throws IOException, InstantiationException, IllegalAccessException {
        
        if (page.getGenerateModes().contains(PageGenerateMode.Disable))
            return;
        
        
        //BeanCopier copier = BeanCopier.create(page.getClass(), page.getClass(), false);
        for (String genterateMode : page.getGenerateModes()) {
            
            //Page currentPage = page.getClass().newInstance();
            //copier.copy(page, currentPage, null);
            page.setEngine(engine);
            page.setCurrentGenerateMode(genterateMode);     //设置当前的生成类型，可选值为：Custom, Insert, Update, Detail
            page.createChildControls();
            
            String pageContent =  page.render();
    
            FileUtils.forceMkdir(new File(GenerateOutputHelper.getOutputPath(page)));
            FileUtils.writeStringToFile(new File(GenerateOutputHelper.getOutputFilePath(page)), pageContent);;
            System.out.println("generate: " + GenerateOutputHelper.getOutputFilePath(page));
        }
    }
    

    public void generate(Model model) throws IOException {

        model.setEngine(engine);
        model.createChildren();
        String modelContent =  model.render();

        FileUtils.forceMkdir(new File(GenerateOutputHelper.getOutputPath(model)));
        FileUtils.writeStringToFile(new File(GenerateOutputHelper.getOutputFilePath(model)), modelContent);
        System.out.println("generate: " + GenerateOutputHelper.getOutputFilePath(model));

        for (Feature feature : model.getFeatures()) {
            feature.render();
            for (CodeFile file: feature.getCodeFiles()) {
                FileUtils.writeStringToFile(new File(GenerateOutputHelper.getOutputFilePath(file)), file.getContent());
                System.out.println("generate: " + GenerateOutputHelper.getOutputFilePath(file));
            }
        }
        
    }



}
