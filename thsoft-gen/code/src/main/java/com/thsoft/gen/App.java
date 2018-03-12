package com.thsoft.gen;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.rythmengine.RythmEngine;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.thsoft.metamodel.domain.*;
import com.thsoft.metamodel.ui.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String applicationRootPath = App.class.getClassLoader().getResource("applications").getPath();
    	ApplicationGenerator generator = new ApplicationGenerator();
    	try {
			generator.generateApplication(applicationRootPath, "mes");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
    }
   

}
