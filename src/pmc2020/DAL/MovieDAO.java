/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pmc2020.BE.Category;
import pmc2020.BE.Movie;

/**
 *
 * @author Guruerne
 */
public class MovieDAO
{

    private DatabaseConnector dbCon;
    
    /**
     * Makes a new instance of a database connector
     * @throws IOException 
     */

    public MovieDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    /**
     * Gets all movies from the DB
     * @return all movies as a list
     * @throws DalException 
     */

    public List<Movie> getAllMovies() throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Movie> allMovies = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("name");
                double imdb_rating = rs.getDouble("imdb_rating");
                double p_rating = rs.getDouble("p_rating");
                String filelocation = rs.getString("filelocation");
                String lastview = rs.getString("lastview");
                String imdb_link = rs.getString("imdb_link");

                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, lastview, imdb_link);
                allMovies.add(mov);
            }
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    
    /**
     * The method to create a movie in the DB
     * @param title
     * @param imdb_rating
     * @param filelocation
     * @param imdb_link
     * @param categories
     * @return the newly created movie, and adds categories to it
     * @throws DalException 
     */

    public Movie createMovie(String title, double imdb_rating, String filelocation, String imdb_link, List<Category> categories) throws DalException
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "INSERT INTO Movie (name, imdb_rating, filelocation, imdb_link) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, title);
            ps.setDouble(2, imdb_rating);
            ps.setString(3, filelocation);
            ps.setString(4, imdb_link);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int ID = rs.getInt(1);
                    String last_view = null;
                    double p_rating = 0;
                    
                    Movie mov = new Movie(ID, title, imdb_rating, p_rating, filelocation, last_view, imdb_link);


                    addCategorytoMovie(ID, categories);
                    return mov;
                }
            }

            throw new DalException();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    
    /**
     * Deletes a movie from the DB
     * @param movie
     * @throws DalException 
     */

    public void deleteMovie(Movie movie) throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            int id = movie.getID();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Movie WHERE id=?");

            ps.setInt(1, id);
            
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                System.out.println("It's Dead!");
            }
            if (affectedRows != 1)
            {
                throw new DalException();
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    
    /**
     * Updates a movie in the DB with the given data
     * @param movie
     * @throws DalException 
     */

    public void updateMovie(Movie movie) throws DalException

    {
        try ( Connection con = dbCon.getConnection())
        {
            int id = movie.getID();
            String title = movie.getTitle();
            double imdb_rating = movie.getIMDB_Rating();
            double p_rating = movie.getPrivate_Rating();
            String filelocation = movie.getFile_location();
            String lastview = movie.getLast_Viewed();
            String imdb_link = movie.getIMDB_Link();

            PreparedStatement ps = con.prepareStatement("UPDATE Movie SET name=?, imdb_rating=?, p_rating=?, filelocation=?, lastview=?, imdb_link=? WHERE id=?");

            ps.setString(1, title);
            ps.setDouble(2, imdb_rating);
            ps.setDouble(3, p_rating);
            ps.setString(4, filelocation);
            ps.setString(5, lastview);
            ps.setString(6, imdb_link);
            ps.setInt(7, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                System.out.println("It's Updated!");
            }
            if (affectedRows != 1)
            {
                throw new DalException();
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    
    /**
     * Gets all movies from a certain category
     * @param categories
     * @return all movies as a list, from a given category
     * @throws SQLException
     * @throws DalException 
     */

    public List<Movie> getMoviesPerCategory(int categories) throws SQLException, DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            //System.out.println(categories);

            String sql = "SELECT DISTINCT Movie.* FROM CatMovie as catmovie \n"
                    + "JOIN Movie as movie ON catmovie.MovieId = movie.id \n"
                    + "JOIN Category as category ON catmovie.CategoryId = category.id \n"
                    + "WHERE category.id IN (" + categories + ")";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);;

            ArrayList<Movie> allMovies = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("name");
                double imdb_rating = rs.getDouble("imdb_rating");
                double p_rating = rs.getDouble("p_rating");
                String filelocation = rs.getString("filelocation");
                String lastview = rs.getString("lastview");
                String imdb_link = rs.getString("imdb_link");

                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, lastview, imdb_link);
                allMovies.add(mov);
            }
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }

    }
    
    /**
     * Adds chosen categories to a movie
     * @param id
     * @param category
     * @throws SQLException 
     */

    public void addCategorytoMovie(int id, List<Category> category) throws SQLException
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO CatMovie (movieId, categoryId) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < category.size(); i++)
            {

                ps.setInt(1, id);
                ps.setInt(2, category.get(i).getCategory_ID());

                ps.executeUpdate();

            }
        }
    }
}
