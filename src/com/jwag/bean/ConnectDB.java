/*
 * ConnectDB.java
 *
 * Created on 03 June 2007, 19:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jwag.bean;


/**
 *
 * @author Saifo
 */
public class ConnectDB {
    
    private static ConnectDB singleton = null;
    private String localhost;
    private String dbname;
    private String dbport;
    private String dbusrname;
    private String dbpaswrd;
    
    
    /**
     * Creates a new instance of ConnectDB
     */
    private ConnectDB() {}
    
    /**
     * 
     * @return 
     */
    public static ConnectDB getIntance(){
        
        /**
         * create the new Instace if 
         * object is not created
         * or return the same if already created 
         */
        if(singleton == null){
            // create a new instance
            singleton = new ConnectDB();
        }
        return singleton;
    }
    
    /**
     * 
     * @return 
     */
    public String getLocalhost() {
        return localhost;
    }

    /**
     * 
     * @param localhost 
     */
    public void setLocalhost(String localhost) {
        this.localhost = localhost;
    }

    /**
     * 
     * @return 
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * 
     * @param dbname 
     */
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    /**
     * 
     * @return 
     */
    public String getDbport() {
        return dbport;
    }

    /**
     * 
     * @param dbport 
     */
    public void setDbport(String dbport) {
        this.dbport = dbport;
    }

    /**
     * 
     * @return 
     */
    public String getDbusrname() {
        return dbusrname;
    }

    /**
     * 
     * @param dbusrname 
     */
    public void setDbusrname(String dbusrname) {
        this.dbusrname = dbusrname;
    }

    /**
     * 
     * @return 
     */
    public String getDbpaswrd() {
        return dbpaswrd;
    }

    /**
     * 
     * @param dbpaswrd 
     */
    public void setDbpaswrd(String dbpaswrd) {
        this.dbpaswrd = dbpaswrd;
    }
    
}
