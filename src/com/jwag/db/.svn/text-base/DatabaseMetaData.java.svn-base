/*
 * DatabaseMetaData.java
 *
 * Created on 21 August 2007, 23:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jwag.db;

import com.jwag.bean.ConnectDB;
import com.jwag.bean.ColumnDetail;
import com.jwag.gui.JwagMain;
import com.jwag.util.GenUtil;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Saifo
 */
public class DatabaseMetaData {
    
    private Connection connObject;
    private PreparedStatement frm_ptmt;
    private Statement frm_stmt;
    private ResultSet frm_Rs;
    private String QueryString = "";
    private ConnectDB conDB;
    private ColumnDetail columndetail;
    
    /**
     *
     * @throws java.lang.Exception
     * @return
     */
    public List<String> getTablesName()throws Exception{
        
        frm_Rs = getDatabaseMetaData().getTables(null,null,null,null);
        List<String> tableNames = new ArrayList<String>();
        //System.out.println("here i am in getTablesName");
        
        while(frm_Rs.next()) {
            
            //System.out.println("Table Name : "+frm_Rs.getString("TABLE_NAME"));
            tableNames.add(frm_Rs.getString("TABLE_NAME"));
            
        } //while
        
        frm_Rs.close();
        DBConnection.closeConnection(connObject);
        
        return tableNames;
    } //getTable Method
    
    /**
     *
     * @param tablename
     * @throws java.sql.SQLException
     * @return
     */
    public List<String> getColumnsName(String tablename) throws SQLException{
        
        System.out.println("inside getcloumns");
        frm_Rs = getDatabaseMetaData().getColumns(null,null,tablename,null);
        List <String> columnData = new ArrayList <String>();
        
        while (frm_Rs.next()) {
            
            columnData.add(frm_Rs.getString("COLUMN_NAME"));
            
        }
        
        frm_Rs.close();
        DBConnection.closeConnection(connObject);
        
        return columnData;
    } //getColumns
    
    /**
     *
     * @param choice
     * @throws java.sql.SQLException
     * @return
     */
    public List<ColumnDetail> getSelectedColumns(String choice) throws SQLException{
        
        String tablename = JwagMain.tableName;
        frm_Rs = getDatabaseMetaData().getColumns(null,null,tablename,null);
        //frm_Rs = getDatabaseMetaData().getPrimaryKeys(null,null,tableName);
        List <ColumnDetail> columnData = new ArrayList <ColumnDetail>();
        
        while (frm_Rs.next()) {
            
            for(String columnname : JwagMain.columnNames){
                
                if(columnname.equals((String)frm_Rs.getString("COLUMN_NAME"))){
                    
                    if(!choice.equals("{record.")){
                        
                        columndetail = new ColumnDetail();
                        columndetail.setTableName(tablename);
                        columndetail.setColumnName(frm_Rs.getString("COLUMN_NAME"));
                        columndetail.setColumnJavaName(GenUtil.firstToUpperCase(frm_Rs.getString("COLUMN_NAME")));
                        columndetail.setColumnJavaType(GenUtil.getJavaType(frm_Rs.getString("DATA_TYPE")));
                        columndetail.setColumnSize(frm_Rs.getInt("COLUMN_SIZE"));
                        
                        columnData.add(columndetail);
                        
                    } else {
                        
                        columndetail = new ColumnDetail();
                        columndetail.setTableName(tablename);
                        columndetail.setColumnName(frm_Rs.getString("COLUMN_NAME"));
                        columndetail.setColumnJavaName(GenUtil.firstToUpperCase(frm_Rs.getString("COLUMN_NAME")));
                        columndetail.setJstlGetExpr(choice+columndetail.getColumnName()+"}");
                        columndetail.setColumnJavaType(GenUtil.getJavaType(frm_Rs.getString("DATA_TYPE")));
                        columndetail.setColumnTypeName(frm_Rs.getString("TYPE_NAME"));
                        //System.out.println("Data Type Name "+frm_Rs.getString( "TYPE_NAME" )+" DATA TYPE "+frm_Rs.getString("DATA_TYPE"));
                        columndetail.setColumnSize(frm_Rs.getInt("COLUMN_SIZE"));
                        
                        columnData.add(columndetail);
                    }
                    
                }//if
            }//for
        }//while
        
        frm_Rs.close();
        DBConnection.closeConnection(connObject);
        //connObject = DBConnection.closeConnection(connObject);
        
        return columnData;
    } //getSelectedColumns
    
    /**
     *
     * @param choice
     * @throws java.sql.SQLException
     * @return
     */
    public List<ColumnDetail> getColumnsDetails(String choice) throws SQLException{
        
        String tablename = JwagMain.tableName;
        frm_Rs = getDatabaseMetaData().getColumns(null,null,tablename,null);
        //frm_Rs = getDatabaseMetaData().getPrimaryKeys(null,null,tableName);
        List <ColumnDetail> columnData = new ArrayList <ColumnDetail>();
        
        while (frm_Rs.next()) {
            
            if(!choice.equals("{record.")){
                
                columndetail = new ColumnDetail();
                columndetail.setTableName(tablename);
                columndetail.setColumnName(frm_Rs.getString("COLUMN_NAME"));
                columndetail.setColumnJavaName(GenUtil.firstToUpperCase(frm_Rs.getString("COLUMN_NAME")));
                columndetail.setColumnJavaType(GenUtil.getJavaType(frm_Rs.getString("DATA_TYPE")));
                columndetail.setColumnSize(frm_Rs.getInt("COLUMN_SIZE"));
                
                columnData.add(columndetail);
                
            } else {
                
                columndetail = new ColumnDetail();
                columndetail.setTableName(tablename);
                columndetail.setColumnName(frm_Rs.getString("COLUMN_NAME"));
                columndetail.setColumnJavaName(GenUtil.firstToUpperCase(frm_Rs.getString("COLUMN_NAME")));
                columndetail.setJstlGetExpr(choice+columndetail.getColumnName()+"}");
                columndetail.setColumnJavaType(GenUtil.getJavaType(frm_Rs.getString("DATA_TYPE")));
                columndetail.setColumnSize(frm_Rs.getInt("COLUMN_SIZE"));
                
                columnData.add(columndetail);
            }
            
        }//while
        
        frm_Rs.close();
        DBConnection.closeConnection(connObject);
        
        
        return columnData;
    } //getSelectedColumns
    
    /**
     *
     * @throws java.sql.SQLException
     * @return
     */
    public Map<String,Object> getSelectedColumnType()throws SQLException{
        
        String tablename = JwagMain.tableName;
        frm_Rs = getDatabaseMetaData().getColumns(null,null,tablename,null);
        Map<String,Object> columnType =new HashMap<String,Object>();
        
        while (frm_Rs.next()) {
            
            for(String columnname : JwagMain.columnNames){
                
                if(columnname.equals((String)frm_Rs.getString("COLUMN_NAME"))){
                    
                    int jdbcTypeCode = frm_Rs.getShort( "DATA_TYPE" );
                    columnType.put(frm_Rs.getString("COLUMN_NAME"),GenUtil.getSqlType(jdbcTypeCode));
                    
                }
            }//for
        }//while
        return columnType;
    }
    
    /**
     *
     * @throws java.sql.SQLException
     * @return
     */
    public Map<String,Object> getColumnType()throws SQLException{
        
        String tablename = JwagMain.tableName;
        frm_Rs = getDatabaseMetaData().getColumns(null,null,tablename,null);
        Map<String,Object> columnType =new HashMap<String,Object>();
        
        while (frm_Rs.next()) {
            
            int jdbcTypeCode = frm_Rs.getShort( "DATA_TYPE" );
            columnType.put(frm_Rs.getString("COLUMN_NAME"),GenUtil.getSqlType(jdbcTypeCode));
            
        }//while
        
        return columnType;
    }
    
    public boolean hasPrimaryKey(String tableName)throws SQLException{
        
        boolean flag = false;
        
        frm_Rs = getDatabaseMetaData().getPrimaryKeys(null,null,tableName);
        String columnName ="";
        if(frm_Rs.next()){
            
            columnName = frm_Rs.getString("COLUMN_NAME");
            
            if(!columnName.equalsIgnoreCase("id")){
                
                JOptionPane.showMessageDialog(null,"The Table must have id column INTEGER Data Type with parimary key and auto increment ","Alert",JOptionPane.INFORMATION_MESSAGE);
            }else{
                flag = true;
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"The Table must have id column INTEGER Data Type with parimary key and auto increment ","alert",JOptionPane.INFORMATION_MESSAGE);
        }
        return flag;
    }
    
    /**
     *
     * @throws java.sql.SQLException
     * @return
     */
    private java.sql.DatabaseMetaData getDatabaseMetaData() throws SQLException {
        
        conDB = ConnectDB.getIntance();
        connObject = DBConnection.getDBConnection(conDB);
        //System.out.println("Metadata "+connObject.getMetaData().toString());
        return connObject.getMetaData();
        
    }
    
}
