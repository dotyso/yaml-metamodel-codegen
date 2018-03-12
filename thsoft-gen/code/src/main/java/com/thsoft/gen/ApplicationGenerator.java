package com.thsoft.gen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.rythmengine.RythmEngine;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.thsoft.metamodel.domain.Model;
import com.thsoft.metamodel.application.ContextConfig;
import com.thsoft.metamodel.application.Module;
import com.thsoft.gen.templateengine.RythmTemplateEngine;
import com.thsoft.gen.templateengine.TemplateEngine;
import com.thsoft.gen.utils.ClassScaner;
import com.thsoft.gen.utils.PathUtils;
import com.thsoft.metamodel.application.Application;
import com.thsoft.metamodel.ui.Page;

public class ApplicationGenerator {

    private TemplateEngine engine;
    private Set<Class<?>> yamlBeanClasses;
    private CodeGenerator codeGenerator;
    private ArchetypeGenerator archetypeGenerator;
    private ContextConfig contextConfig;
    
    private static ApplicationGenerator applicationGenerator;

    public ApplicationGenerator() {
        engine = new RythmTemplateEngine();
        codeGenerator = new CodeGenerator(engine);
        archetypeGenerator = new ArchetypeGenerator();
    }
    
    public void generateApplication(String applicationRootPath, String applicationId) throws IOException, InstantiationException, IllegalAccessException {

        try {
            String applicationPath = applicationRootPath + "/" + applicationId;

            contextConfig = (ContextConfig) this.loadYaml(ContextConfig.class, applicationRootPath + "/config.yaml");
            Application application = (Application) this.loadYaml(ContextConfig.class, applicationPath + "/application.yaml");
            application.setId(applicationId);
            application.setApplicationPath(applicationPath);
            //application.setConfig(config);

            //构建application
            buildApplication(application);
            
            //生成代码
            generateFile(application);


        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }
    }

    public TemplateEngine getEngine() {
        return engine;
    }

    private void buildApplication(Application application) throws IOException {

        File file = new File(application.getApplicationPath());
        if (file.exists()) {
            for (File subfile : file.listFiles()) {
                if (subfile.isDirectory()) {
                    String moduleId = subfile.getName();
                    String modulePath = application.getApplicationPath() + "/" + subfile.getName();
                    Module module = loadYaml(Module.class, modulePath + "/module.yaml");
                    module.setId(moduleId);
                    module.setModulePath(modulePath);
                    module.setApplication(application);
                    application.getModules().add(module);
                    
                    //构建module
                    buildModule(module);
                }
            }
        }
    }
    
    private void buildModule(Module module) throws IOException {
        File pagesDir = new File(module.getModulePath() + "/pages");
        if (pagesDir.exists()) {
            for (File pageFile : pagesDir.listFiles()) {
                String pageId = PathUtils.getFileNameWithoutSuffix(pageFile);
                Page page = loadYaml(Page.class, module.getModulePath() + "/pages/" + pageFile.getName());
                page.setId(pageId);
                page.setModule(module);
                module.getPages().add(page);
            }
        }

        File modelsDir = new File(module.getModulePath() + "/models");
        if (modelsDir.exists()) {
            for (File modelFile : modelsDir.listFiles()) {
                String modelId = PathUtils.getFileNameWithoutSuffix(modelFile);
                Model model = loadYaml(Page.class, module.getModulePath() + "/models/" + modelFile.getName());
                model.setId(modelId);
                model.setModule(module);
                module.getModels().add(model);
            }
        }
    }

    private void generateFile(Application application) throws IOException, InstantiationException, IllegalAccessException {

        for (Module module : application.getModules()) {
            
            //生成模块脚手架
            archetypeGenerator.generateModuleArchtype(module);

            //生成页面代码文件
            for (Page page : module.getPages()) {
                codeGenerator.generate(page);
            }
            
            //生成模型代码文件
            for (Model model : module.getModels()) {
                codeGenerator.generate(model);
            }
        }
    }

    public <T> T loadYaml(Class clazz, String yamlFile) throws IOException {
         ClassLoader classLoader = this.getClass().getClassLoader();
        YamlReader reader = new YamlReader(new FileReader(yamlFile));

        if (yamlBeanClasses == null && contextConfig != null) {
            this.yamlBeanClasses = new HashSet<Class<?>>();
            for (String yamlBeanPackage : contextConfig.getYamlBeanPackages()) {
                this.yamlBeanClasses.addAll(ClassScaner.scan(yamlBeanPackage));
            }

        }
        if (yamlBeanClasses != null) {
            for (Class<?> clazz1 : this.yamlBeanClasses) {
                reader.getConfig().setClassTag(clazz1.getSimpleName(), clazz1);
            }
        }

        T cls = (T) reader.read(clazz);
        reader.close();

        return cls;
    }



    public static ApplicationGenerator getInstance() {
        if (applicationGenerator == null)
            applicationGenerator = new ApplicationGenerator();
        return applicationGenerator;
    }
}
