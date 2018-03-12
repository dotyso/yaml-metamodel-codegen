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
import com.thsoft.metamodel.application.GenerateConfig;
import com.thsoft.metamodel.application.Module;
import com.thsoft.gen.templateengine.RythmTemplateEngine;
import com.thsoft.gen.templateengine.TemplateEngine;
import com.thsoft.metamodel.application.Application;
import com.thsoft.metamodel.ui.Page;

public class ApplicationGenerator {

    private static TemplateEngine engine;
    private static Set<Class<?>> scanClass;

    ApplicationGenerator() {

        engine = new RythmTemplateEngine();

        this.scanClass = new HashSet<Class<?>>();
        this.scanClass.addAll(ClassScaner.scan("com.thsoft.metamodel.domain"));
        this.scanClass.addAll(ClassScaner.scan("com.thsoft.metamodel.domain.feature"));
        this.scanClass.addAll(ClassScaner.scan("com.thsoft.metamodel.ui"));
        this.scanClass.addAll(ClassScaner.scan("com.thsoft.metamodel.ui.controls"));
    }

    public static TemplateEngine getEngine() {
        return engine;
    }

    public void GenerateApplication(String applicationName) throws IOException {

        try {
            String applicationPath = "applications/" + applicationName;
            String applicationAbsolutePath = this.getClass().getClassLoader().getResource(applicationPath).getPath();

            GenerateConfig config = (GenerateConfig) this.loadYaml(GenerateConfig.class, "applications/config.yaml");
            Application application = (Application) this.loadYaml(GenerateConfig.class, applicationPath + "/application.yaml");
            application.setApplicationPath(applicationPath);
            application.setApplicationAbsolutePath(applicationAbsolutePath);
            application.setConfig(config);

            buildApplication(application);

            generateSource(application);


        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }
    }

    private void generateSource(Application application) throws IOException {

        for (Module module : application.getModules()) {
            createModuleArchtype(module);
            generateModuleCode(module);
        }


    }

    private void createModuleArchtype(Module module) throws IOException {
        String moduleOutputPath = module.getApplication().getConfig().getOutput();

        FileUtils.forceMkdir(new File(moduleOutputPath));

    }

    private void generateModuleCode(Module module) throws IOException {
        for (Page page : module.getPages()) {

            String pageContent = this.renderPage(page, engine);

            FileUtils.forceMkdir(new File(page.getOutputPath()));
            FileUtils.writeStringToFile(new File(page.getOutputFilePath()), pageContent);;
            System.out.println("generate: " + page.getOutputFilePath());
        }

        for (Model model : module.getModels()) {
            String moduleContent = this.renderModel(model, engine);

            /*
             * FileUtils.forceMkdir(new File(model.getOutputPath()));
             * FileUtils.writeStringToFile(new File(model.getOutputFilePath()), modelContent);;
             * System.out.println("generate: " + model.getOutputFilePath());
             */
        }
    }

    private void buildApplication(Application application) throws IOException {

        File file = new File(application.getApplicationAbsolutePath());
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    String moduleAbsolutePath = application.getApplicationAbsolutePath() + "/" + file2.getName();
                    String modulePath = application.getApplicationPath() + "/" + file2.getName();
                    Module module = loadYaml(Module.class, modulePath + "/module.yaml");
                    module.setModulePath(modulePath);
                    module.setModuleAbsolutePath(moduleAbsolutePath);
                    module.setApplication(application);
                    application.getModules().add(module);
                    buildModule(module);
                }
            }
        }

    }

    private void buildModule(Module module) throws IOException {
        File pagesDir = new File(module.getModuleAbsolutePath() + "/pages");
        if (pagesDir.exists()) {
            for (File pageYaml : pagesDir.listFiles()) {
                Page page = loadYaml(Page.class, module.getModulePath() + "/pages/" + pageYaml.getName());
                page.setModule(module);
                module.getPages().add(page);
            }
        }

        File modelsDir = new File(module.getModuleAbsolutePath() + "/models");
        if (modelsDir.exists()) {
            for (File modelYaml : modelsDir.listFiles()) {
                Model model = loadYaml(Page.class, module.getModulePath() + "/models/" + modelYaml.getName());
                module.getModels().add(model);
            }
        }
    }

    public String renderModel(Model model, TemplateEngine engine) {
        model.setEngine(engine);
        return model.render();
    }

    public String renderPage(Page page, TemplateEngine engine) {
        page.setEngine(engine);
        page.init();
        return page.render();
    }

    public <T> T loadYaml(Class clazz, String yamlFile) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        YamlReader reader = new YamlReader(new FileReader(classLoader.getResource(yamlFile).getFile()));

        for (Class<?> clazz1 : this.scanClass) {
            reader.getConfig().setClassTag(clazz1.getSimpleName(), clazz1);
        }
        // reader.getConfig().setClassTag("MesInsertPage", MesInsertPage.class);

        T cls = (T) reader.read(clazz);
        reader.close();

        return cls;
    }
}
