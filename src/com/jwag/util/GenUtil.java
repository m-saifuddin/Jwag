/*
 * GenUtil.java
 *
 * Created on 01 July 2007, 05:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */package com.jwag.util;


import java.sql.Types;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Saifo
 */
public abstract class GenUtil {
    
    private final static int CAPACITY = 41;
    
    /**
     * 
     * @param string 
     * @return 
     */
    public static String firstToUpperCase(String string) {
        
        String post = string.substring(1,string.length());
        String first = (""+string.charAt(0)).toUpperCase();
        return first+post;
        
    }
    
    /**
     * 
     * @param underscoreSeparatedString 
     * @param capitalize 
     * @return 
     */
    public static String toCamelCase(String underscoreSeparatedString, boolean capitalize) {
        
        String[] parts = StringUtils.split(underscoreSeparatedString, '_');
        
        for (int i = 1; i < parts.length; i++) {
            
            parts[i] = WordUtils.capitalize(parts[i]);
            
        }
        String camelCasedString = StringUtils.join(parts, ' ').replaceAll("\\s+", "");
        
        if (capitalize) {
            
            return WordUtils.capitalize(camelCasedString);
            
        } else{
            
            return camelCasedString;
        }
    }
        
    /**
     * 
     * @param template 
     * @param beanName 
     * @return 
     */
    public static String MergeTemplateName(String template, String beanName){
        
        String mergeName = "";
        
        if(template.equals("DaoImpl.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"DaoImpl"});
            
        } else if(template.equals("Dao.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"Dao"});
            
        } else if(template.equals("RowMapper.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"RowMapper"});
            
        } else if(template.equals("MappingSqlQuery.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"MappingSqlQuery"});
            
        } else if(template.equals("ActionController.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"ActionController"});
            
        } else if(template.equals("AddFormController.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"AddFormController"});
            
        } else if(template.equals("EditFormController.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"EditFormController"});
            
        } else if(template.equals("view.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"_view"});
            
        } else if(template.equals("edit.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"_edit"});
            
        }  else if(template.equals("list.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"_list"});
            
        } else if(template.equals("add.vm")){
            
            mergeName = StringUtils.join(new String[]{beanName,"_add"});
            
        }
        
        return mergeName;
        
        
    }//mergeTemplate

    /**
     * 
     * @param sqlType 
     * @return 
     */
    public static String getJavaType(String sqlType) {
        
        String javaType = "";
        
        switch (Integer.parseInt(sqlType)){
            
            case -7 : javaType ="Boolean";
            break;
            
            case -5 : javaType ="Long";
            break;
            
            case -1 : javaType ="String";
            break;
            
            case 1 : javaType ="String";
            break;
            
            case 2 : javaType ="java.math.BigDecimal";
            break;
            
            case 3 : javaType ="Double";
            break;
            
            case 4 : javaType ="Integer";
            break;
            
            case 5 : javaType ="Integer";
            break;
            
            case 6 : javaType ="Float";
            break;
            
            case 7 : javaType ="Float";
            break;
            
            case 8 : javaType ="Double";
            break;
            
            case 12 : javaType ="String";
            break;
            
            case 16 : javaType ="Boolean";
            break;
            
            case 91 : javaType ="Date";
            break;
            
            case 92 : javaType ="java.sql.Time";
            break;
            
            case 93 : javaType ="java.sql.Timestamp";
            break;
            
            default :  if(javaType == null){
                
                return sqlType;
            }
        }
        
        return javaType;
    }
    
    /**
     * 
     * @param sqlType 
     * @return 
     */
    public static String getSqlType(int sqlType){
        
        return ((String)(codeToName.get( new Integer( sqlType ) )));
    }
    
    private static HashMap codeToName;
    static
    {
        codeToName = new HashMap( CAPACITY );
        codeToName.put( new Integer( Types.ARRAY ), "ARRAY" );
        codeToName.put( new Integer( Types.BIGINT ), "BIGINT" );
        codeToName.put( new Integer( Types.BINARY ), "BINARY" );
        codeToName.put( new Integer( Types.BIT ), "BIT" );
        codeToName.put( new Integer( Types.BLOB ), "BLOB" );
        codeToName.put( new Integer( Types.CHAR ), "CHAR" );
        codeToName.put( new Integer( Types.CLOB ), "CLOB" );
        codeToName.put( new Integer( Types.DATE ), "DATE" );
        codeToName.put( new Integer( Types.DECIMAL ), "DECIMAL" );
        codeToName.put( new Integer( Types.DISTINCT ), "DISTINCT" );
        codeToName.put( new Integer( Types.DOUBLE ), "DOUBLE" );
        codeToName.put( new Integer( Types.FLOAT ), "FLOAT" );
        codeToName.put( new Integer( Types.INTEGER ), "INTEGER" );
        codeToName.put( new Integer( Types.JAVA_OBJECT ),"JAVA_OBJECT" );
        codeToName.put( new Integer( Types.LONGVARBINARY ),"LONGVARBINARY" );
        codeToName.put( new Integer( Types.LONGVARCHAR ),"LONGVARCHAR" );
        codeToName.put( new Integer( Types.NULL ), "NULL" );
        codeToName.put( new Integer( Types.NUMERIC ), "NUMERIC" );
        codeToName.put( new Integer( Types.OTHER ), "OTHER" );
        codeToName.put( new Integer( Types.REAL ), "REAL" );
        codeToName.put( new Integer( Types.REF ), "REF" );
        codeToName.put( new Integer( Types.SMALLINT ), "SMALLINT" );
        codeToName.put( new Integer( Types.STRUCT ), "STRUCT" );
        codeToName.put( new Integer( Types.TIME ), "TIME" );
        codeToName.put( new Integer( Types.TIMESTAMP ),"TIMESTAMP" );
        codeToName.put( new Integer( Types.TINYINT ), "TINYINT" );
        codeToName.put( new Integer( Types.VARBINARY ),"VARBINARY" );
        codeToName.put( new Integer( Types.VARCHAR ), "VARCHAR" );
    }
    
    
    
    
}
