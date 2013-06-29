package com.jwag.gen;

import com.jwag.bean.ColumnDetail;
import com.jwag.gui.JwagMain;
import com.jwag.util.GenUtil;
import com.jwag.db.DatabaseMetaData;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;

/*
 * BeanGenerator.java
 *
 * Created on 02 September 2007, 03:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Saifo
 */
public class BeanGenerator {
    
    private String templateName;
    private TemplateEngine templateEngine;
    private String webAppName = JwagMain.applicationName;
    private DatabaseMetaData dbMetaData;
    
    /**
     *
     * @param tableName
     * @param projectPackage
     * @throws java.lang.Exception
     * @return
     */
    public String generate(String tableName,String projectPackage) throws Exception {
        
        /**** Initialize TemplateEngine */
        templateEngine = TemplateEngine.getInstance();
        dbMetaData = new DatabaseMetaData();
        Map<String,Object> model = new HashMap<String,Object>();
        String beanName = getBeanName(tableName);
        List<ColumnDetail> columnDataList = getColumnDetail();
        
        model.put("Column_Details", columnDataList);
        model.put("packageName", StringUtils.join(new String[] {projectPackage, "bean"}, '.'));
        model.put("beanName", beanName);
        
        templateName = TemplateEngineUtil.getTemplate("beanTemplate.vm",templateEngine);
        writeDataToFile(templateEngine, templateName, model, projectPackage, tableName);
        
        return beanName;
    }
    
    /**
     *
     * @param targetPackage
     * @param tableName
     * @throws java.lang.Exception
     * @return
     */
    private File getTargetFilePath(String targetPackage, String tableName) throws Exception {
        File targetFile = new File(StringUtils.join(new String[] {
            webAppName+"/src/" +
                    StringUtils.replace(targetPackage, ".", File.separator),
            "bean",getBeanName(tableName) + ".java" }, File.separatorChar));
        File targetDir = targetFile.getParentFile();
        if (! targetDir.exists()) {
            
            FileUtils.forceMkdir(targetDir);
            }
            return targetFile;
    }
        
        /**
         *
         * @param templateEngine
         * @param templateName
         * @param model
         * @param targertPackage
         * @param tableName
         * @throws java.lang.Exception
         */
        private void writeDataToFile(TemplateEngine templateEngine, String templateName, Map model, String targertPackage,
                String tableName)throws Exception{
            
            String output = TemplateEngineUtil.mergeTemplateIntoString(templateEngine, templateName, model);
            FileUtils.writeStringToFile(getTargetFilePath(targertPackage, tableName), output, "UTF-8");
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
         * @throws java.lang.Exception
         * @return
         */
        private List<ColumnDetail> getColumnDetail() throws Exception {
            String choice = "bean";
            
            List<ColumnDetail> columnDataHolders = dbMetaData.getColumnsDetails(choice);
            
            return columnDataHolders;
        }
    }
    
    
