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
 * @author CSnit
 */
public class MovieManager
{

    private MovieDAO movieDAO;
    private CategoryDAO categoryDAO;

    public MovieManager() throws IOException
    {
        movieDAO = new MovieDAO();
        categoryDAO = new CategoryDAO();
    }

    public Movie addMovie(String title, double p_rating, double imdb_rating, String filelocation, String imdb_link)
    {
        return movieDAO.createMovie(title, p_rating, imdb_rating, filelocation, imdb_link);
    }

    public List<Movie> getAllMovies()
    {
        return movieDAO.getAllMovies();
    }

    public void deleteMovie(Movie movie)
    {
        movieDAO.deleteMovie(movie);
    }

    public void updateMovie(Movie movie)
    {
        movieDAO.updateMovie(movie);
    }

    /**
     * Searches the list of movies
     * @param query
     * @return result of what you've searched for
     */
    public List<Movie> search(String query) throws DalException
    {
        List<Movie> movieSearchBase = movieDAO.getAllMovies();
        List<Category> categorySearchBase = categoryDAO.getAllCategories();
        List<Movie> result = new ArrayList<>();

        for (Movie movie : movieSearchBase)
        {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(movie);
            }
            for (Category category : categorySearchBase) //TEST OM DET HER VIRKER
            {
                if (category.getCategory().toLowerCase().contains(query.toLowerCase()))
                {
                    result.add(movie);
                }
            } // Tilføj search funktion til personal rating
        }
        return result;
    }

}
