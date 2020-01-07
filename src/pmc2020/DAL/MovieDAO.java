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

import pmc2020.BE.Movie;

/**
 *
 * @author CSnit
 */
public class MovieDAO
{

    private DatabaseConnector dbCon;

    public MovieDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    
    public static void main(String[] args) throws DalException, IOException, SQLException
    {
        MovieDAO movDAO = new MovieDAO();
        String categories = "1, 4";
        
        List<Movie> catMovies = movDAO.getMoviesPerCategory(categories);
        for (Movie catmovie : catMovies)
        {
            System.out.println(catmovie);
        }
    }

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

                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, imdb_link, lastview);
                allMovies.add(mov);
            }
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }

    public Movie createMovie(String title, double imdb_rating, double p_rating, String filelocation, String imdb_link) throws DalException
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "INSERT INTO Movie (name, imdb_rating, p_rating, filelocation, imdb_link) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, title);
            ps.setDouble(2, imdb_rating);
            ps.setDouble(3, p_rating);
            ps.setString(4, filelocation);
            ps.setString(5, imdb_link);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    String last_view = null;

                    Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, last_view, imdb_link);
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

    public void updateMovie(Movie movie) throws DalException

    {
        try ( Connection con = dbCon.getConnection())
        {
            int id = movie.getID();
            String title = movie.getTitle();
            double imdb_rating = movie.getIMDB_Rating();
            double p_rating = movie.getPrivate_rating();
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

    public List<Movie> getMoviesPerCategory(String categories) throws SQLException, DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            System.out.println(categories);
            
            String sql = "SELECT DISTINCT Movie.* FROM CatMovie as catmovie \n" +
                         "JOIN Movie as movie ON catmovie.MovieId = movie.id \n" +
                         "JOIN Category as category ON catmovie.CategoryId = category.id \n" +
                         "WHERE category.id IN ("+categories+")";
            PreparedStatement ps = con.prepareStatement(sql);
            
          
            ResultSet rs = ps.executeQuery();
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

                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, imdb_link, lastview);
                allMovies.add(mov);
            }
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }    
            

            
    }
}
