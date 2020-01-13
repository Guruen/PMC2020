/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import pmc2020.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pmc2020.BE.Category;
import pmc2020.BE.Movie;
import pmc2020.DAL.DalException;

/**
 *
 * @author Guruerne
 */
public class MovieModel
{

    private MovieManager movieManager;
    private ObservableList<Movie> allMovies;
    private ObservableList<Category> allCategories;
    
    /**
     * Makes a new MovieModel, and adds all movies and categories
     * @throws IOException
     * @throws DalException 
     */

    public MovieModel() throws IOException, DalException
    {
        movieManager = new MovieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        allCategories = FXCollections.observableArrayList();
        allCategories.addAll(movieManager.getAllCategories());

    }

    
    /**
     * gets an ObservableList of all movies
     * @return all movies
     */
    
    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }

    
    /**
     * gets an ObservableList of all categories
     * @return all categories
     */
    
    public ObservableList<Category> getAllCategories()
    {
        return allCategories;
    }

    
    /**
     * Deletes the chosen movie
     * @param movie
     * @throws DalException 
     */
    
    public void deleteMovie(Movie movie) throws DalException
    {
        movieManager.deleteMovie(movie);
        allMovies.remove(movie);
    }
    
    /**
     * Adds a newly created movie to the DB
     * @param ID
     * @param Title
     * @param IMDB_Rating
     * @param File_location
     * @param imdb_Link
     * @param last_view
     * @param categories
     * @throws DalException 
     */
    
    public void addMovie(int ID, String Title, double IMDB_Rating, String File_location, String imdb_Link, String last_view, List<Category> categories) throws DalException, IOException
    {
        Movie movie = movieManager.addMovie(Title, IMDB_Rating, File_location, imdb_Link, categories);
        allMovies.add(movie);
    }

    
    /**
     * Updates an existing movie in the DB
     * @param movie
     * @throws DalException 
     */
    
    public void updateMovie(Movie movie) throws DalException
    {
        movieManager.updateMovie(movie);
        allMovies.remove(movie);
        allMovies.add(movie);
    }

    
    /**
     * Searches for something with the given term, query or category
     * @param query
     * @throws DalException 
     */
    
    public void search(String query) throws DalException, IOException
    {
        if (query.isEmpty())
        {
            allMovies.clear();
            allMovies.addAll(movieManager.getAllMovies());
        } else
        {
            allMovies.clear();
            allMovies.addAll(movieManager.search(query));
        }
    }
    
    /**
     * Searches within a given category/filter
     * @param categoryToSearch
     * @throws SQLException
     * @throws DalException 
     */

    public void searchByCategory(int categoryToSearch) throws SQLException, DalException, IOException
    {
        if (categoryToSearch == 0)
        {
            allMovies.clear();
            allMovies.addAll(movieManager.getAllMovies());
        } else
        {
            allMovies.clear();
            allMovies.addAll(movieManager.searchByCategory(categoryToSearch));
        }
    }
    
    /**
     * Searches the DB by IMDB rating
     * @param minIMDBRating
     * @param maxIMDBRating
     * @throws DalException
     * @throws IOException 
     */

    public void searchByIMDBRating(double minIMDBRating, double maxIMDBRating) throws DalException, IOException
    {
        allMovies.clear();
        allMovies.addAll(movieManager.searchByIMDBRating(minIMDBRating, maxIMDBRating));
    }
    
    /**
     * Searches the DB by personal rating
     * @param minPersonRating
     * @param maxPersonRating
     * @throws DalException
     * @throws IOException 
     */

    public void searchByPersonalRating(double minPersonRating, double maxPersonRating) throws DalException, IOException
    {
        allMovies.clear();
        allMovies.addAll(movieManager.searchByPersonalRating(minPersonRating, maxPersonRating));
    }
    
    /**
     * Adds a movie to the DB
     * @param title
     * @param iMDB_Rating
     * @param iMDB_SiteLink
     * @param movie_FilePath
     * @param categories
     * @throws DalException 
     */

    public void addMovie(String title, double iMDB_Rating, String iMDB_SiteLink, String movie_FilePath, List<Category> categories) throws DalException, IOException
    {
        Movie movie = movieManager.addMovie(title, iMDB_Rating, iMDB_SiteLink, movie_FilePath, categories);
        allMovies.add(movie);
    }

    
    /**
     * refreshes the list of movies, so it matches with the DB
     * @return all movies in the DB
     * @throws DalException 
     */
    
    public ObservableList<Movie> refreshMovies() throws DalException, IOException
    {
        allMovies.clear();
        allMovies.addAll(movieManager.getAllMovies());
        return allMovies;
    }

    
    /**
     * Creates a category and adds it to the DB
     * @param category
     * @throws DalException 
     */
    
    public void createCategory(String category) throws DalException
    {
        Category cat = movieManager.createCategory(category);
        allCategories.add(cat);
    }
    
    /**
     * Edits an already existing category within the DB
     * @param category
     * @throws DalException 
     */

    public void editCategory(Category category) throws DalException
    {
        movieManager.editCategory(category);
        allCategories.clear();
        allCategories.addAll(movieManager.getAllCategories());
    }
    
    /**
     * Deletes a category from the DB entirely
     * @param category
     * @throws DalException 
     */

    public void deleteCategory(Category category) throws DalException
    {
        movieManager.deleteCategory(category);
        allCategories.clear();
        allCategories.addAll(movieManager.getAllCategories());
    }

    
    public void movieSearch(String titleSearch, double highP_rating, double lowP_rating, double highIMDB_rating, double lowIMDB_rating) throws DalException, IOException
    {
        allMovies.clear();
        allMovies.addAll(movieManager.movieSearch(titleSearch, highP_rating, lowP_rating, highIMDB_rating, lowIMDB_rating));
        System.out.println(movieManager.movieSearch(titleSearch, highP_rating, lowP_rating, highIMDB_rating, lowIMDB_rating));
    }
}
