/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.management.modelmbean.ModelMBean;
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
    private Movie movie;

    public MovieManager() throws IOException
    {
        movieDAO = new MovieDAO();
        categoryDAO = new CategoryDAO();
    }

    public Movie addMovie(String title, double imdb_rating, String filelocation, String imdb_link, List<Category> categories) throws DalException
    {
        return movieDAO.createMovie(title, imdb_rating, filelocation, imdb_link, categories);
    }

    public List<Movie> getAllMovies() throws DalException
    {
        return movieDAO.getAllMovies();
    }

    public void deleteMovie(Movie movie) throws DalException
    {
        movieDAO.deleteMovie(movie);
    }

    public void updateMovie(Movie movie) throws DalException
    {
        movieDAO.updateMovie(movie);
    }

    public List<Category> getAllCategories() throws DalException
    {
        return categoryDAO.getAllCategories();
    }

    /**
     * Searches the list of movies
     *
     * @param query
     * @return result of what you've searched for
     */
    public List<Movie> search(String query) throws DalException
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

    public List<Movie> searchByCategory(int categoryToSearch) throws SQLException, DalException
    {
        List<Movie> movieSearchBase = movieDAO.getMoviesPerCategory(categoryToSearch);
        List<Movie> result = new ArrayList<>();

        result.addAll(movieDAO.getMoviesPerCategory(categoryToSearch));

        return result;
    }

    public Category createCategory(String category) throws DalException
    {
        return categoryDAO.createCategory(category);
    }

    public List<Category> getCategoryPerMovie(int movieid) throws SQLException, DalException
    {

        return categoryDAO.getCategoryPerMovie(movieid);
    }
}
