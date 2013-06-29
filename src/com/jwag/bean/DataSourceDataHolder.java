/*
 * DataSourceDataHolder.java
 *
 * Created on 17 September 2007, 03:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jwag.bean;

/**
 *
 * @author Saifo
 */
public class DataSourceDataHolder {
    
    /** Creates a new instance of DataSourceDataHolder */
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private String hostName;
    private String databaseName;

    /**
     * 
     * @return 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return 
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
            
}
