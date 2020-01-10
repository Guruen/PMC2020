/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author Guruerne
 */

public class DatabaseConnector
{
    private SQLServerDataSource dataSource;
    
    /**
     * Reads the settings file to get access to the DB
     * @throws IOException 
     */
    
    public DatabaseConnector() throws IOException
    {
        Properties props = new Properties();
        props.load(new FileReader("DBSettings.txt"));
        
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }
    
    /**
     * Gets connection the DB, given that the properties are correct
     * @return The connection the DB
     * @throws SQLServerException 
     */
    
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
    
}
