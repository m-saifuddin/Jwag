package com.jwag.gen;

import com.jwag.bean.DataSourceDataHolder;
import com.jwag.gui.JwagMain;
import com.jwag.util.GenUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Generates the ApplicationContext file for the application.
 * @author Sujit Pal (spal@users.sourceforge.net)
 * @version $Revision: 1.2 $
 */
public class ApplicationContextGenerator {
    
    private TemplateEngine templateEngine;
    private String webAppName = JwagMain.applicationName;
    private String[] jsptemplates = {"list.vm", "view.vm", "edit.vm"};
    private String beanName;
    private String beanObj;
    private String daoName;
    private String daoObj;
    private String daoImplName;
    private String daoImplObj;
    
    /**
     *
     * @param tableName
     * @param jspname
     * @param dataSourceDataHolder
     * @param daonames
     * @param controllerName
     * @param projectPackage
     * @throws java.lang.Exception
     */
    public void generate(String tableName, ArrayList<String> jspname, DataSourceDataHolder dataSourceDataHolder, ArrayList<String> daonames, ArrayList<String> controllerName, String projectPackage) throws Exception {
        
        //intializing the templateEngine
        templateEngine = TemplateEngine.getInstance();
        
        beanName = getBeanName(tableName);
        beanObj = GenUtil.toCamelCase(tableName, false);
        
        for (String dao : daonames) {
            
            String daoStr = StringUtils.uncapitalize(dao);
            
            if (dao.equals(beanName + "Dao")) {
                
                daoName = GenUtil.toCamelCase(daoStr, true);
                daoObj = GenUtil.toCamelCase(daoStr, false);
                System.out.println("  " + daoName + " " + daoObj);
            } else if (dao.equals(beanName + "DaoImpl")) {
                
                daoImplName = GenUtil.toCamelCase(daoStr, true);
                daoImplObj = GenUtil.toCamelCase(daoStr, false);
                System.out.println("  " + daoImplName + " " + daoImplObj);
            }
        }
        
        Map<String, Object> model = new HashMap<String, Object>();
        
        model.put("controllerName", controllerName);
        model.put("projectPackage", projectPackage);
        model.put("jspname", jspname);
        model.put("jsptemplates", jsptemplates);
        model.put("dataSource", dataSourceDataHolder);
        model.put("daoName", daoName);
        model.put("daoObj", daoObj);
        model.put("daoImplName", daoImplName);
        model.put("daoImplObj", daoImplObj);
        model.put("beanName", beanName);
        model.put("beanObj", beanObj);
        
        String templateName = TemplateEngineUtil.getTemplate("servlet.vm", templateEngine);
        writeDataToFile(templateEngine, templateName, model);
    }// generate method
    
    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private File getTargetFile() throws Exception {
        File targetFile = new File(webAppName+"/war/WEB-INF/" + webAppName + "-servlet.xml");
        File targetDir = targetFile.getParentFile();
        System.out.println(targetDir.getPath());
        if (!(targetDir.exists())) {
            FileUtils.forceMkdir(targetDir);
        }
        
        return targetFile;
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
     * @param tableName
     * @return
     */
    private String getBeanName(String tableName) {
        
        return GenUtil.toCamelCase(tableName, true);
    }
}