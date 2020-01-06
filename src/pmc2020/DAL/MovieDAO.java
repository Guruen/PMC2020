/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import java.io.IOException;

/**
 *
 * @author CSnit
 */
public class MovieDAO
{
    
    private DatabaseConnector dbCon;
    
    public MovieDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    
}
