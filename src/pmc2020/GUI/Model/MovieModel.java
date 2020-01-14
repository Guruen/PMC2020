/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Model;

import java.io.IOException;
import java.text.ParseException;
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

    public List<Movie> checkDate() throws DalException, IOException, ParseException
    {
        return movieManager.checkDate();
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
    
    /**
     * Handles searching for movies
     * @param titleSearch
     * @param highP_rating
     * @param lowP_rating
     * @param highIMDB_rating
     * @param lowIMDB_rating
     * @throws DalException
     * @throws IOException 
     */

    
    public void movieSearch(String titleSearch, double highP_rating, double lowP_rating, double highIMDB_rating, double lowIMDB_rating, int categoryid) throws DalException, IOException
    {
        allMovies.clear();
        allMovies.addAll(movieManager.movieSearch(titleSearch, highP_rating, lowP_rating, highIMDB_rating, lowIMDB_rating, categoryid));
    }
}
