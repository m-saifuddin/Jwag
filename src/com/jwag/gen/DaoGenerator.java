/*
 * DaoGenerator.java
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
public class DaoGenerator {

    private TemplateEngine templateEngine;
    private DatabaseMetaData dbMetaData;
    private String webAppName = JwagMain.applicationName;
    private String beanName;
    private String beanObjName;

    public ArrayList<String> generate(String tableName, String projectPackage) throws Exception {

        /**** Initialize VelocityEngine */
        templateEngine = TemplateEngine.getInstance();
        ArrayList<String> generatedfiles = new ArrayList<String>();
        dbMetaData = new DatabaseMetaData();

        String daoName = "";
        String[] templates = {"Dao.vm", "DaoImpl.vm", "RowMapper.vm", "MappingSqlQuery.vm"};

        beanName = getBeanName(tableName);
        beanObjName = GenUtil.toCamelCase(tableName, false);

        List<ColumnDetail> columnDetails = getColumnDetail();
        List<Map<String, Object>> columnTypelist = getColumnSqlType();

        


        int beanSize = columnDetails.size();

        Map<String, Object> model = new HashMap<String, Object>();

        for (String template : templates) {

            String templateName = TemplateEngineUtil.getTemplate(template, templateEngine);
            daoName = GenUtil.MergeTemplateName(templateName, beanName);

            if (template.equals("RowMapper.vm") || template.equals("DaoImpl.vm")) {

                List<Map<String, Object>> selectedColumnType = getSelectedColumnSqlType();
                List<ColumnDetail> selectedColumnDetails = getSelectedColumnDetails();
                
                model.put("selectedColumnDetails", selectedColumnDetails);
                model.put("selectedColumnTypes", selectedColumnType);
            }

            model.put("columnDetails", columnDetails);
            model.put("columnTypelist", columnTypelist);
            model.put("beanObj", beanObjName);
            model.put("beanSize", beanSize);
            model.put("beanName", beanName);
            model.put("tableName", tableName);
            model.put("packageName", StringUtils.join(new String[]{projectPackage, "dao"}, '.'));
            model.put("daoName", daoName);
            model.put("projectPackage", projectPackage);

            writeDataToFile(templateEngine, templateName, model, projectPackage, daoName);

            if (templateName.equals("Dao.vm") || templateName.equals("DaoImpl.vm")) {
                generatedfiles.add(daoName);
            }
        }
        return generatedfiles;
    }

    /**
     *
     * @param targetPackage
     * @param fileName
     * @throws java.lang.Exception
     * @return
     */
    private File getTargetFilePath(String targetPackage, String fileName) throws Exception {
        File targetFile = new File(StringUtils.join(new String[]{webAppName + "/src/" + StringUtils.replace(targetPackage, ".", File.separator), "dao", fileName + ".java"}, File.separatorChar));
        File targetDir = targetFile.getParentFile();

        if (!targetDir.exists()) {

            FileUtils.forceMkdir(targetDir);
        } /*else {
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
     * @param targertPackage
     * @param fileName
     * @throws java.lang.Exception
     */
    private void writeDataToFile(TemplateEngine templateEngine, String templateName, Map model, String targertPackage, String fileName) throws Exception {

        String output = TemplateEngineUtil.mergeTemplateIntoString(templateEngine, templateName, model);
        FileUtils.writeStringToFile(getTargetFilePath(targertPackage, fileName), output, "UTF-8");
    }

    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<ColumnDetail> getColumnDetail() throws Exception {

        String choice = "bean";
        
        List<ColumnDetail> columnData = dbMetaData.getColumnsDetails(choice);

        return columnData;
    }

    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<ColumnDetail> getSelectedColumnDetails() throws Exception {

        String choice = "bean";
        
        List<ColumnDetail> columnData = dbMetaData.getSelectedColumns(choice);

        return columnData;
    }

    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<Map<String, Object>> getColumnSqlType() throws Exception {

        Map<String, Object> sqlType = new HashMap<String, Object>();
        List<Map<String, Object>> listSqlType = new ArrayList<Map<String, Object>>();
        sqlType = dbMetaData.getColumnType();
        listSqlType.add(sqlType);

        return listSqlType;
    }

    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    private List<Map<String, Object>> getSelectedColumnSqlType() throws Exception {

        Map<String, Object> sqlType = new HashMap<String, Object>();
        List<Map<String, Object>> listSqlType = new ArrayList<Map<String, Object>>();
        sqlType = dbMetaData.getSelectedColumnType();
        listSqlType.add(sqlType);

        return listSqlType;
    }
}