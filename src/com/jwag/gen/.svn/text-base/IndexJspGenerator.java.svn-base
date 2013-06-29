// $Id: IndexJspGenerator.java,v 1.2 2007/07/15 21:02:02 spal Exp $
// $Source: /cvsroot/autocrud/autocrud/src/main/java/net/sf/autocrud/IndexJspGenerator.java,v $
package com.jwag.gen;

import com.jwag.gui.JwagMain;
import com.jwag.util.GenUtil;
import com.jwag.db.DatabaseMetaData;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

/**
 * TODO: Class level Javadocs
 * @author Sujit Pal (spal@users.sourceforge.net)
 * @version $Revision: 1.2 $
 */
public class IndexJspGenerator {
    
    private TemplateEngine velocityEngine = TemplateEngine.getInstance();
    private String templateName = "indexJsp.vm";
    private String webAppName = JwagMain.applicationName;
    private DatabaseMetaData dbMetadataDao;
    private String beanName;
    
    /**
     * 
     * @param tableName 
     * @throws java.lang.Exception 
     */
    public void generate(String tableName) throws Exception {
        
        //VelocityEngine velocityEngine = TemplateEngine.velocityEngine;
        velocityEngine = TemplateEngine.getInstance();
        beanName = getBeanName(tableName);
        
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("webAppName", webAppName);
        model.put("beanName",beanName);
        
        writeDataToFile(velocityEngine, templateName, model);
    }
    
    /**
     * 
     * @throws java.lang.Exception 
     * @return 
     */
    private File getTargetFilePath() throws Exception {
        File targetFile = new File(webAppName+"/war/index.jsp");
        File targetDir = targetFile.getParentFile();
        if (!(targetDir.exists())) {
            
            FileUtils.forceMkdir(targetDir);
        
        }/*else {
            
            int answer = JOptionPane.showConfirmDialog(null," "+ targetFile.getName().toString()+" File Already Exists. Do you want to Modify it","Alert",JOptionPane.INFORMATION_MESSAGE);
            
            if (answer == JOptionPane.YES_OPTION) {
                
                FileUtils.forceMkdir(targetDir);
                
                JOptionPane.showMessageDialog(null,"File Successfully Updated","Confirmation",JOptionPane.INFORMATION_MESSAGE);
                
            } else if (answer == JOptionPane.NO_OPTION) {
                
            }
        }*/
        return targetFile;
    }
    
    /**
     * 
     * @param tableName 
     * @return 
     */
    private String getBeanName(String tableName) {
        
        return GenUtil.toCamelCase(tableName, true);
    }
    
    /**
     * 
     * @param templateEngine 
     * @param templateName 
     * @param model 
     * @throws java.lang.Exception 
     */
    private void writeDataToFile(TemplateEngine templateEngine, String templateName, Map model )throws Exception{
        
        String output = TemplateEngineUtil.mergeTemplateIntoString(templateEngine, templateName, model);
        FileUtils.writeStringToFile(getTargetFilePath(), output, "UTF-8");
    }
}
