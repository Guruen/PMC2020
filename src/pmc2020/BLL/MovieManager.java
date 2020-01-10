/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pmc2020.BE.Category;
import pmc2020.BE.Movie;
import pmc2020.DAL.CategoryDAO;
import pmc2020.DAL.DalException;
import pmc2020.DAL.MovieDAO;

/**
 *
 * @author Guruerne
 */
public class MovieManager
{

    private MovieDAO movieDAO;
    private CategoryDAO categoryDAO;
    
    /**
     * @param MovieDAO Imports and defines usage of another class
     * @param CategoryDAO Imports and defines usage of another class
     */
    
    /** 
     * MovieManager uses other java classes (creates other instances) to manage movies
     * @param MovieDAO
     * @param CategoryDAO
     * @throws IOException to make sure that no bugs or errors halt the program
     */

    public MovieManager() throws IOException
    {
        movieDAO = new MovieDAO();
        categoryDAO = new CategoryDAO();
    }
    
    /** 
     * Constructor for movies
     * @param title The title of movie
     * @param imdb_rating rating of movie on IMDB
     * @param filelocation Location of file on disk
     * @param imdb_link Link to the movie on IMDB
     * @param categories The categories that define the movie
     * @return instance of movieDAO.createMovie which creates the movie in the DB, with the supplied data
     * @throws DalException 
     */

    public Movie addMovie(String title, double imdb_rating, String filelocation, String imdb_link, List<Category> categories) throws DalException
    {
        return movieDAO.createMovie(title, imdb_rating, filelocation, imdb_link, categories);
    }
    
    /** 
     * list of movies from DB
     * @return all movies from DB as list
     * @throws DalException 
     */

    public List<Movie> getAllMovies() throws DalException
    {
        return movieDAO.getAllMovies();
    }
    
    /** 
     * Delete Movie from DB
     * @param movie to be deleted
     * @throws DalException 
     */

    public void deleteMovie(Movie movie) throws DalException
    {
        movieDAO.deleteMovie(movie);
    }
    
    /** 
     * Update movie already in DB
     * @param movie to be updated
     * @throws DalException 
     */

    public void updateMovie(Movie movie) throws DalException
    {
        movieDAO.updateMovie(movie);
    }
    
    /** 
     * List of categories from the DB
     * @return all categories in the DB as list
     * @throws DalException 
     */
    
        public List<Category> getAllCategories() throws DalException
    {
        return categoryDAO.getAllCategories();
    }

    /**
     * Searches the list of movies
     * @param query that you are searching for
     * @return result of what you've searched for
     */
    public List<Movie> search(String query) throws DalException
    {
        List<Movie> movieSearchBase = movieDAO.getAllMovies();
        List<Category> categorySearchBase = categoryDAO.getAllCategories(); //Delete
        List<Movie> result = new ArrayList<>();
        
        for (Movie movie : movieSearchBase)
        {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(movie);
            }
        }
        return result;
    }
    
    /** 
     * Constructor for categories
     * @param category The newly created category
     * @return category
     * @throws DalException 
     */
    
    public Category createCategory(String category) throws DalException
    {
        return categoryDAO.createCategory(category);
    }

}
