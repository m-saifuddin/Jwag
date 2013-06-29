/*
 * WebXmlGenerator.java
 */

package com.jwag.gen;

import com.jwag.gui.JwagMain;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Saifo
 */
public class WebXmlGenerator {
    
    private TemplateEngine templateEngine;
    private String webAppName = JwagMain.applicationName;
    
    /**
     *
     * @throws java.lang.Exception
     */
    public void generate() throws Exception {
        
        
        templateEngine = TemplateEngine.getInstance();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("webAppName", webAppName);
        
        String templateName = TemplateEngineUtil.getTemplate("webXml.vm", templateEngine);
        writeDataToFile(templateEngine, templateName, model);
    }
    
    /**
     *
     * @param templateEngine
     * @param templateName
     * @param model
     * @throws java.lang.Exception
     */
    private void writeDataToFile(TemplateEngine templateEngine, String templateName, Map model) throws Exception {
        
        String output = TemplateEngineUtil.mergeTemplateIntoString(templateEngine, templateName, model);
        FileUtils.writeStringToFile(getTargetFile(), output, "UTF-8");
    }
    
    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private File getTargetFile() throws Exception {
        
        File targetFile = new File(webAppName+"/war/WEB-INF/web.xml");
        File targetDir = targetFile.getParentFile();
        if (!(targetDir.exists())) {
            FileUtils.forceMkdir(targetDir);
        }
        
        File createLib = new File(webAppName+"/war/WEB-INF/lib");
        File targetLib = createLib.getAbsoluteFile();
        
        if (!(targetLib.exists())) {
            
            FileUtils.forceMkdir(targetLib);
        }else{
            int answer = JOptionPane.showConfirmDialog(null,"\" "+ targetFile.getName().toString()+"\" File Already Exists with previous Generated Code \n" +
                    "Current code will append the previous. Do you want to Modify it","Alert",JOptionPane.INFORMATION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                FileUtils.forceMkdir(targetDir);
                JOptionPane.showMessageDialog(null,"File Successfully Updated","Confirmation",JOptionPane.INFORMATION_MESSAGE);
            } else if (answer == JOptionPane.NO_OPTION) {
            }
        }
        return targetFile;
    }
}