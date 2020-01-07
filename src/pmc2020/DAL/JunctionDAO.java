/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Brian
 */
public class JunctionDAO
{

    private DatabaseConnector dbCon;

    public JunctionDAO() throws IOException, SQLException
    {
        dbCon = new DatabaseConnector();
               
    }
    
    public static void main(String[] args) 
    {
        System.out.println("WTF!");
    }

}
