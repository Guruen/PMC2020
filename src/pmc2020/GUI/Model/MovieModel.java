/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Model;

import java.io.IOException;
import java.util.Comparator;
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
    
    public void deleteSong(Movie movie) throws DalException
    {
        movieManager.deleteMovie(movie);
        allMovies.remove(movie);
    }
    
    
    public void addMovie(int ID, String Title, double IMDB_Rating, double Private_rating, String File_location, String imdb_Link, String last_view) throws DalException
    {
        Movie movie = movieManager.addMovie(Title, Private_rating, IMDB_Rating, File_location, imdb_Link);
        allMovies.add(movie);
    }
    
    public void updateMovie(Movie movie) throws DalException
    {
        movieManager.updateMovie(movie);
        
        
        
        
        
        
//        if (allMovies.remove(movie))
//        {
//            allMovies.add(movie);
//            allMovies.sort(new Comparator<Movie>()
//            {
//                @Override
//                public int compare(Movie arg0, Movie arg1)
//                {
//                    return arg0.getID() - arg1.getID();
//                }
//            });
//        }
        
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

    public void addMovie(String title, double iMDB_Rating, String iMDB_SiteLink, String movie_FilePath)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
