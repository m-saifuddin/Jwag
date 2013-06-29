/*
 * DBConnection.java
 *
 * Created on 03 June 2007, 18:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jwag.db;

import com.jwag.bean.ConnectDB;
import java.sql.*;

/**
 *
 * @author Saifo
 */
public abstract class DBConnection {
    
    public static Connection connObject = null;
        
    /**
     * 
     * @param conDB 
     * @return 
     */
    public static Connection getDBConnection(ConnectDB conDB) {
        
        connObject = null;
        
        try {
            System.out.println("conDB " +conDB.getDbname());
            System.out.println("conDB " +conDB.getDbpaswrd());
            System.out.println("conDB " +conDB.getDbusrname());
            System.out.println("conDB " +conDB.getDbport());
            System.out.println("conDB " +conDB.getLocalhost());
            
            String constr = "jdbc:mysql://"+ conDB.getLocalhost() +":"+ conDB.getDbport() +"/"+ conDB.getDbname()+"";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connObject = DriverManager.getConnection(constr, conDB.getDbusrname(), conDB.getDbpaswrd());
            java.sql.DatabaseMetaData databasemetadata = connObject.getMetaData();
          
            
            
        } catch(Exception exception) {
            System.out.println("Error In Connection " + exception.toString());
        }
        
        return connObject;
    } //getConnection
    
    /**
     * 
     * @param connObj 
     * @return 
     */
    public static Connection closeConnection(Connection connObj) {
        try {
            if(connObj != null) {
                connObj.close();
                connObj = null;
                
                System.out.println("Connection Close Successfully");
            }
        } catch(Exception exp) {
            System.out.println("Connection Close Failure. Contact System Administrator");
            
        } finally {
            connObj = null;
        }
        return connObj;
    } //close connection
    
    
    
}
