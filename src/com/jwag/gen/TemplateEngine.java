/*
 * TemplateEngine.java
 *
 * Created on 02 September 2007, 02:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jwag.gen;

import java.util.Properties;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author Saifo
 */
public class TemplateEngine extends VelocityEngine{
    
    //public static VelocityEngine velocityEngine = null;
    private static TemplateEngine singleton = null;
        
    /**
     * Constructs a new CodeGenerator
     */
    private TemplateEngine() {}
    
    
   /**
    * create the new Instace if 
    * object is not created
    * or return the same if already created 
    */
    public static TemplateEngine getInstance() {
        if (singleton == null) {
            // create a new instance
            singleton = new TemplateEngine();
            
            try{
                
                singleton.initialize();
                
            }catch(Exception ex){
                
                System.out.println("Error in initializing VelocityEngine : "+ex.getMessage());
            }
        }
        
        return singleton;
    }
    
    /**
     * Initializes the CodeGenerator engine if not already initialized
     * Initializes the internal VelocityEngine with this CodeGenerator's
     * properties.  All the properties have default values.
     * This method will simply return if this CodeGenerator is already initialized.
     * @throws Exception if problem initializing the engine
     */
    public void initialize() throws Exception {
        
        
        Properties engineProperties = new Properties();
        engineProperties.put("resource.loader", "file");
        engineProperties.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        engineProperties.put("file.resource.loader.path", "./resources");
        // create a new instance of the engine
        
        singleton.init(engineProperties);
    }
    
    
    
}
