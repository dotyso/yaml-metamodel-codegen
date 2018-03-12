package com.thsoft.gen;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.thsoft.metamodel.application.Module;

public class ArchetypeGenerator {

    public void generateModuleArchtype(Module module) throws IOException {
        String moduleOutputPath = module.getApplication().getJavaProjectOutputPath();

        FileUtils.forceMkdir(new File(moduleOutputPath));

    }
}
