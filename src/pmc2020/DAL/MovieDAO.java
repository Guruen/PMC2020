/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import java.util.List;
import pmc2020.BE.Movie;

/**
 *
 * @author CSnit
 */
public class MovieDAO
{

  
    public List<Movie> GetAllMovies()
    {
        return null;
    }
    
    public Movie CreateMovie()
    {
        
        return null;
    }
    
    public void DeleteMovie()
    {
        
        
    }
    
    public Movie UpdateMovie()
    {
        
        return null;
    }
    
    
    private DatabaseConnector dbCon;
    
    public MovieDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    
}

