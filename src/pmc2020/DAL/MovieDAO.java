/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import java.io.IOException;
import java.util.List;
import pmc2020.BE.Movie;

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
  
    public List<Movie> getAllMovies()
    {
        return null;
    }
    
    public Movie createMovie(String title, double p_rating, double imdb_rating, String filelocation, String imdb_link)
    {

        return null;
    }

            
    
    public void deleteMovie(Movie movie)
        {   

    }

    
    public Movie updateMovie(Movie movie)

    {

        return null;
    }

}
