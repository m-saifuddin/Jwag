/*
 * JspGenerator.java
 */

package com.jwag.gen;

import com.jwag.bean.ColumnDetail;
import com.jwag.gui.JwagMain;
import com.jwag.util.GenUtil;
import com.jwag.db.DatabaseMetaData;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author Saifo
 */
public class JspGenerator {
    
    private TemplateEngine templateEngine = TemplateEngine.getInstance();
    private String webAppName = JwagMain.applicationName;
    private String[] jsptemplates = {"list.vm", "view.vm", "edit.vm", "add.vm"};
    private ArrayList<String> jspnames;
    private String beanName;
    private String beanObj;
    private DatabaseMetaData dbMetadata;
    
    /**
     *
     * @param tableName
     * @param beanName
     * @throws java.lang.Exception
     * @return
     */
    public ArrayList<String> generate(String tableName, String beanName) throws Exception {
        /**** Initialize VelocityEngine */
        templateEngine = TemplateEngine.getInstance();
        jspnames = new ArrayList<String>();
        dbMetadata = new DatabaseMetaData();
        
        beanName = getBeanName(tableName);
        beanObj = GenUtil.toCamelCase(tableName, false);
        Map<String, Object> model = new HashMap<String, Object>();
        List<ColumnDetail> columnDetails = getColumnDetail();
        
        for (String template : jsptemplates) {
            
            String templateName = TemplateEngineUtil.getTemplate(template, templateEngine);
            String jspName = GenUtil.MergeTemplateName(templateName, beanName);
            
            if (template.equals("list.vm")) {
                
                List<ColumnDetail> selectedColumnDetails = getSelectedColumnDetails();
                model.put("selectedColumnDetails", selectedColumnDetails);
            }
            
            model.put("webAppName", webAppName);
            model.put("beanName", beanName);
            model.put("beanObj", beanObj);
            model.put("jspName", jspName);
            model.put("columnDetails", columnDetails);
            
            
            writeDataToFile(templateEngine, templateName, model, jspName);
            
            jspnames.add(jspName);
        }
        
        return jspnames;
    }
    
    /**
     *
     * @param templateEngine
     * @param templateName
     * @param model
     * @param fileName
     * @throws java.lang.Exception
     */
    private void writeDataToFile(TemplateEngine templateEngine, String templateName, Map model, String fileName) throws Exception {
        
        String output = TemplateEngineUtil.mergeTemplateIntoString(templateEngine, templateName, model);
        FileUtils.writeStringToFile(getTargetFilePath(fileName), output, "UTF-8");
    }
    
    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<ColumnDetail> getColumnDetail() throws Exception {
        
        String choice = "{record.";
        List<ColumnDetail> columnDataHolders = dbMetadata.getColumnsDetails(choice);
        
        return columnDataHolders;
    }
    
    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<ColumnDetail> getSelectedColumnDetails() throws Exception {
        
        String choice = "{record.";
        List<ColumnDetail> columnData = dbMetadata.getSelectedColumns(choice);
        
        return columnData;
    }
    
    /**
     *
     * @param jspName
     * @throws java.lang.Exception
     * @return
     */
    private File getTargetFilePath(String jspName) throws Exception {
        File targetFile = new File(StringUtils.join(new String[]{webAppName + "/war/WEB-INF/", 
        "jsp", jspName + ".jsp"}, File.separatorChar));
        
        File targetDir = targetFile.getParentFile();
        if (!targetDir.exists()) {
            
            FileUtils.forceMkdir(targetDir);
        }
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
}