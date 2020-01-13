/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BLL;

import java.io.IOException;
import java.sql.SQLException;
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

    public Movie addMovie(String title, double imdb_rating, String filelocation, String imdb_link, List<Category> categories) throws DalException, IOException
    {
        return movieDAO.createMovie(title, imdb_rating, filelocation, imdb_link, categories);
    }
    
    /** 
     * list of movies from DB
     * @return all movies from DB as list
     * @throws DalException 
     */

    public List<Movie> getAllMovies() throws DalException, IOException
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
    public List<Movie> search(String query) throws DalException, IOException
    {
        List<Movie> movieSearchBase = movieDAO.getAllMovies();
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
     * Searches the list of movies with a certain category as a filter
     * @param query that you are searching for
     * @return result of what you've searched for
     */

    public List<Movie> searchByCategory(int categoryToSearch) throws SQLException, DalException, IOException
    {
        List<Movie> result = new ArrayList<>();

        result.addAll(movieDAO.getMoviesPerCategory(categoryToSearch));

        return result;
    }
    
    /**
     * Searches by the maximum and minimum IMDB rating value according to the sliders
     * @param minRating
     * @param maxRating
     * @return List of relevant movies
     * @throws DalException
     * @throws IOException 
     */

    public List<Movie> searchByIMDBRating(double minRating, double maxRating) throws DalException, IOException
    {
        List<Movie> movieSearchBase = movieDAO.getAllMovies();
        List<Movie> result = new ArrayList<>();

        for (Movie movie : movieSearchBase)
        {
            if (movie.getIMDB_Rating() >= minRating && movie.getIMDB_Rating() <= maxRating)
            {
                result.add(movie);
            }
        }
        return result;
    }
    
    /**
     * Searches by the maximum and minimum user rating value according to the sliders
     * @param minPersonRating
     * @param maxPersonRating
     * @return List of relevant movies
     * @throws DalException
     * @throws IOException 
     */

    public List<Movie> searchByPersonalRating(double minPersonRating, double maxPersonRating) throws DalException, IOException
    {
        List<Movie> movieSearchBase = movieDAO.getAllMovies();
        List<Movie> result = new ArrayList<>();

        for (Movie movie : movieSearchBase)
        {
            if (movie.getPrivate_Rating() >= minPersonRating && movie.getPrivate_Rating() <= maxPersonRating)
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
    
    /**
     * Gets a list of all categories from a chosen movie from the DB
     * @param movieid
     * @return the list of categories that the chosen movie belongs in
     * @throws SQLException
     * @throws DalException 
     */
    
    public List<Category> getCategoryPerMovie(int movieid) throws SQLException, DalException
    {

        return categoryDAO.getCategoryPerMovie(movieid);
    }
    
    /**
     * Updates or edits the chosen category
     * @param category
     * @throws DalException 
     */

    public void editCategory(Category category) throws DalException
    {
        categoryDAO.updateCategory(category);
    }
    
    /**
     * deletes the chosen category
     * @param category
     * @throws DalException 
     */

    public void deleteCategory(Category category) throws DalException
    {
        categoryDAO.deleteCategory(category);
    }
    
    public List<Movie> movieSearch(String titleSearch, double highP_rating, double lowP_rating, double highIMDB_rating, double lowIMDB_rating) throws DalException, IOException
    {
        return movieDAO.movieSearch(titleSearch, highP_rating, lowP_rating, highIMDB_rating, lowIMDB_rating);
        
        
    }

}
