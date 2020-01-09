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
 * @author CSnit
 */
public class MovieModel
{

    private MovieManager movieManager;
    private ObservableList<Movie> allMovies;
    private ObservableList<Category> allCategories;

    public MovieModel() throws IOException, DalException
    {
        movieManager = new MovieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        allCategories = FXCollections.observableArrayList();
        allCategories.addAll(movieManager.getAllCategories());
    }
    
    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }
    
    public ObservableList<Category> getAllCategories()
    {
        return allCategories;
    }
    
    public void deleteMovie(Movie movie) throws DalException
    {
        movieManager.deleteMovie(movie);
        allMovies.remove(movie);
    }
    
    public void addMovie(int ID, String Title, double IMDB_Rating, String File_location, String imdb_Link, String last_view, List<Category> categories) throws DalException
    {
        Movie movie = movieManager.addMovie(Title, IMDB_Rating, File_location, imdb_Link, categories);
        allMovies.add(movie);
    }
    
    public void updateMovie(Movie movie) throws DalException
    {
        movieManager.updateMovie(movie);
        allMovies.remove(movie);
        allMovies.add(movie);
    }
    
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

    public void addMovie(String title, double iMDB_Rating, String iMDB_SiteLink, String movie_FilePath, List<Category> categories) throws DalException
    {
        movieManager.addMovie(title, iMDB_Rating, iMDB_SiteLink, movie_FilePath, categories);
    }
    
    public ObservableList<Movie> refreshMovies() throws DalException
    {
        allMovies.clear();
        allMovies.addAll(movieManager.getAllMovies());
        return allMovies;
    }
    
    public void createCategory(String category) throws DalException
    {
        Category cat = movieManager.createCategory(category);
        allCategories.add(cat);
    }

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
