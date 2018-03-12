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

    	ApplicationGenerator generator = new ApplicationGenerator();
    	try {
			generator.GenerateApplication("mes");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
   

}
