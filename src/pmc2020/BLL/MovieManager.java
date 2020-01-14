/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Movie movie;

    /**
     * @param MovieDAO Imports and defines usage of another class
     * @param CategoryDAO Imports and defines usage of another class
     */
    /**
     * MovieManager uses other java classes (creates other instances) to manage
     * movies
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
     *
     * @param title The title of movie
     * @param imdb_rating rating of movie on IMDB
     * @param filelocation Location of file on disk
     * @param imdb_link Link to the movie on IMDB
     * @param categories The categories that define the movie
     * @return instance of movieDAO.createMovie which creates the movie in the
     * DB, with the supplied data
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
     * makes a list of movies where it checks the date of the movie
     * @return result
     * @throws DalException
     * @throws IOException
     * @throws ParseException 
     */

    public List<Movie> checkDate() throws DalException, IOException, ParseException
    {
        List<Movie> movieSearchBase = movieDAO.getAllMovies();
        List<Movie> result = new ArrayList<>();

        LocalDate todaysDate = LocalDate.now();
        LocalDate twoYearsAgo = todaysDate.minusDays(729);

        for (Movie movie : movieSearchBase)
        {
            if (movie.getLast_Viewed() == null)
            {

            } else
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate lastViewed = LocalDate.parse(movie.getLast_Viewed(), formatter);

                if (lastViewed.isBefore(twoYearsAgo) && (movie.getPrivate_Rating() < 6))
                {
                    result.add(movie);
                }
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
    
    /**
     * Handles searching for movies either with or without filters
     * @param titleSearch
     * @param highP_rating
     * @param lowP_rating
     * @param highIMDB_rating
     * @param lowIMDB_rating
     * @param categoryid
     * @return a list of movies
     * @throws DalException
     * @throws IOException 
     */
    
    public List<Movie> movieSearch(String titleSearch, double highP_rating, double lowP_rating, double highIMDB_rating, double lowIMDB_rating, int categoryid) throws DalException, IOException
    {
        return movieDAO.movieSearch(titleSearch, highP_rating, lowP_rating, highIMDB_rating, lowIMDB_rating, categoryid);
    }

}
