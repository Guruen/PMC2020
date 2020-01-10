/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Model;

import java.io.IOException;
import java.util.Comparator;
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
    
    public void addMovie(int ID, String Title, double IMDB_Rating, String File_location, String imdb_Link, String last_view, List<Category> categories) throws DalException
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
   
        if (allMovies.remove(movie))
        {
            allMovies.add(movie);
            allMovies.sort(new Comparator<Movie>()
            {
                @Override
                public int compare(Movie arg0, Movie arg1)
                {
                    return arg0.getID() - arg1.getID();
                }
            });
        }
        
    }
    
    /**
     * Searches for something with the given term, query or category
     * @param query
     * @throws DalException 
     */
    
    public void search(String query) throws DalException
    {
        if (query.isEmpty())
        {
            allMovies.clear();
            allMovies.addAll(movieManager.getAllMovies());
        }
        else
        {
            allMovies.clear();
            allMovies.addAll(movieManager.search(query));
        }
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

    public void addMovie(String title, double iMDB_Rating, String iMDB_SiteLink, String movie_FilePath, List<Category> categories) throws DalException
    {
        movieManager.addMovie(title, iMDB_Rating, iMDB_SiteLink, movie_FilePath, categories);
    }
    
    /**
     * refreshes the list of movies, so it matches with the DB
     * @return all movies in the DB
     * @throws DalException 
     */
    
    public ObservableList<Movie> refreshMovies() throws DalException
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
     * Searches in the DB with the given category as a filter
     * @param categoryToSearch
     * @throws DalException 
     */

    public void searchCategory(String categoryToSearch) throws DalException
    {
                if (categoryToSearch.isEmpty())
        {
            allMovies.clear();
            allMovies.addAll(movieManager.getAllMovies());
        }
        else
        {
            allMovies.clear();
            allMovies.addAll(movieManager.search(categoryToSearch));
        }
    }




}
