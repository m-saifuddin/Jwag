/*
 * ControllerGenerator.java
 */

package com.jwag.gen;

import com.jwag.bean.ColumnDetail;
import com.jwag.util.GenUtil;
import com.jwag.db.DatabaseMetaData;
import com.jwag.gui.JwagMain;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Saifo
 */
public class ControllerGenerator {

    private TemplateEngine templateEngine;
    private DatabaseMetaData dbMetadata;
    private String webAppName = JwagMain.applicationName;
    private String beanName;
    private String beanObjName;
    private String controllerName = "";
    private String daoName = "";
    private String daoObj = "";
    private String daoImplName = "";
    private String daoImplObj = "";

    /**
     *
     * @param tableName
     * @param jspnames
     * @param daonames
     * @param projectPackage
     * @throws java.lang.Exception
     * @return
     */
    public ArrayList<String> generate(String tableName, ArrayList<String> jspnames, ArrayList<String> daonames, String projectPackage) throws Exception {

        templateEngine = TemplateEngine.getInstance();

        String[] templates = {"EditFormController.vm", "AddFormController.vm", "ActionController.vm"};

        ArrayList<String> controllerNameList = new ArrayList();
        
        dbMetadata = new DatabaseMetaData();
        beanName = getBeanName(tableName);
        beanObjName = GenUtil.toCamelCase(tableName, false);

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

        List<ColumnDetail> columnDetails = getColumnDetail();
        List<Map<String, Object>> listcolumnTypelist = getColumnSqlType();
        Map<String, Object> model = new HashMap<String, Object>();

        for (String template : templates) {

            String templateName = TemplateEngineUtil.getTemplate(template, templateEngine);
            controllerName = GenUtil.MergeTemplateName(templateName, beanName);

            model.put("columnTypelist", listcolumnTypelist);
            model.put("jspnames", jspnames);
            model.put("beanObj", beanObjName);
            model.put("beanName", beanName);
            model.put("daoName", daoName);
            model.put("daoObj", daoObj);
            model.put("tableName", tableName);
            model.put("columnDetails", columnDetails);
            model.put("packageName", StringUtils.join(new String[]{projectPackage, "web"}, '.'));
            model.put("projectPackage", projectPackage);
            model.put("controllerName", controllerName);

            writeDataToFile(templateEngine, templateName, model, projectPackage, controllerName);
            controllerNameList.add(controllerName);
        }

        return controllerNameList;
    }

    /**
     *
     * @param templateEngine
     * @param templateName
     * @param model
     * @param targertPackage
     * @param fileName
     * @throws java.lang.Exception
     */
    private void writeDataToFile(TemplateEngine templateEngine, String templateName, Map model, String targertPackage, String fileName) throws Exception {

        String output = TemplateEngineUtil.mergeTemplateIntoString(templateEngine, templateName, model);
        FileUtils.writeStringToFile(getTargetFilePath(targertPackage, fileName), output, "UTF-8");
    }

    private List<ColumnDetail> getColumnDetail() throws Exception {

        String choice = "bean";
        List<ColumnDetail> columnData = dbMetadata.getColumnsDetails(choice);

        return columnData;
    }

    /**
     *
     * @param targetPackage
     * @param fileName
     * @throws java.lang.Exception
     * @return
     */
    private File getTargetFilePath(String targetPackage, String fileName) throws Exception {
        File targetFile = new File(StringUtils.join(new String[]{webAppName + "/src/" + StringUtils.replace(targetPackage, ".", File.separator), "web", fileName + ".java"}, File.separatorChar));
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

    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<Map<String, Object>> getColumnSqlType() throws Exception {

        Map<String, Object> sqlType = new HashMap<String, Object>();
        List<Map<String, Object>> listSqlType = new ArrayList<Map<String, Object>>();
        sqlType = dbMetadata.getColumnType();
        listSqlType.add(sqlType);

        return listSqlType;
    }
}