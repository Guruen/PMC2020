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

    /**
     * @param MovieDAO Imports and defines usage of another class
     * @param CategoryDAO Imports and defines usage of another class
     */
    /**
     * MovieManager uses other java classes (creates other instances) to manage
     * movies
     *
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
     * calls the "createMovie" method in MovieDAO aswell as send the information
     * required.
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
     * calls the "getAllMovies" method in MovieDAO and then returns a list of
     * all movies in the Database.
     *
     * @return all movies from DB as list
     * @throws DalException
     */
    public List<Movie> getAllMovies() throws DalException, IOException
    {
        return movieDAO.getAllMovies();
    }

    /**
     * calls "deleteMovie" method in MovieDAO and then sends the object marked
     * for deletion.
     *
     * @param movie to be deleted
     * @throws DalException
     */
    public void deleteMovie(Movie movie) throws DalException
    {
        movieDAO.deleteMovie(movie);
    }

    /**
     * calls "updateMovie" from MovieDAO and sends the object to be updated.
     *
     * @param movie to be updated
     * @throws DalException
     */
    public void updateMovie(Movie movie) throws DalException
    {
        movieDAO.updateMovie(movie);
    }

    /**
     * calls the "getAllCategories" method in MovieDAO and returns a list of all
     * categories in the database.
     *
     * @return all categories in the DB as list
     * @throws DalException
     */
    public List<Category> getAllCategories() throws DalException
    {
        return categoryDAO.getAllCategories();
    }

    /**
     * Makes a list of movie objects and checks the last viewed date on movies
     * in the database and checks the private rating. If a movie has last been
     * watched before 2 years ago and has a private rating of less than 6, the
     * movie is added to a list and will display on the screen for the user to
     * see.
     *
     * @return list of movie objects that fulfill the criteria stated
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
            if (movie.getLast_Viewed() != null)
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate lastViewed = LocalDate.parse(movie.getLast_Viewed(), formatter);

                if (lastViewed.isBefore(twoYearsAgo) == true && (movie.getPrivate_Rating() < 6))
                {
                    result.add(movie);
                }
            }
        }
        return result;
    }

    /**
     * calls "createCategory" method in MovieDAO and sends a category String
     * aswell as returning the new category.
     *
     * @param category The newly created category
     * @return category
     * @throws DalException
     */
    public Category createCategory(String category) throws DalException
    {
        return categoryDAO.createCategory(category);
    }

    /**
     * calls "getCategoryPerMovie" method in MovieDAO and sends down a single
     * movie Id, then returns all categories assigned to that movie.
     *
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
     * calls "updateCategory" method in MovieDAO and send down a category
     * object.
     *
     * @param category
     * @throws DalException
     */
    public void editCategory(Category category) throws DalException
    {
        categoryDAO.updateCategory(category);
    }

    /**
     * calls "deleteCategory" method in MovieDAO and sends down the category
     * object to be deleted.
     *
     * @param category
     * @throws DalException
     */
    public void deleteCategory(Category category) throws DalException
    {
        categoryDAO.deleteCategory(category);
    }

    /**
     * calls "movieSearch" method in MovieDAO by sending all the movie
     * attributes and returning a list of movies with those attributes.
     *
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
