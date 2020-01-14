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

    public List<Movie> getAllMovies() throws DalException, IOException
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
                String cats = getCategoriesforMovie(rs.getInt("id"));

                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, lastview, imdb_link, cats);
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

    public Movie createMovie(String title, double imdb_rating, String filelocation, String imdb_link, List<Category> categories) throws DalException, IOException
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "INSERT INTO Movie (name, imdb_rating, filelocation, imdb_link, p_rating) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, title);
            ps.setDouble(2, imdb_rating);
            ps.setString(3, filelocation);
            ps.setString(4, imdb_link);
            ps.setDouble(5, 0.0);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int ID = rs.getInt(1);
                    
                    addCategorytoMovie(ID, categories);
                    
                    String last_view = null;
                    double p_rating = 0.0;
                    String cats = getCategoriesforMovie(rs.getInt(1));
                
                    Movie mov = new Movie(ID, title, imdb_rating, p_rating, filelocation, last_view, imdb_link, cats);

                    
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

    public List<Movie> getMoviesPerCategory(int categories) throws SQLException, DalException, IOException
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
                String cats = getCategoriesforMovie(id);
                
                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, lastview, imdb_link, cats);
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
    
    /**
     * Gets a list of categories that a movie is in
     * @param movieid
     * @return categories
     * @throws IOException
     * @throws SQLException
     * @throws DalException 
     */
    
      public String getCategoriesforMovie(int movieid) throws IOException, SQLException, DalException
  {
      CategoryDAO catDAO = new CategoryDAO();
      
      List<Category> movCat = catDAO.getCategoryPerMovie(movieid);
      String categories = "";
      
      for (int i = 0; i < movCat.size(); i++)
      {
          if (categories == "")
          {
          categories = movCat.get(i).getCategory();
          }
          else{
          categories = categories + " - " + movCat.get(i).getCategory();
          }
      }
      return categories;
  }
      
      
      /**
       * Searches the entries for relevant movies within the given filter(s)
       * @param titleSearch
       * @param highP_rating
       * @param lowP_rating
       * @param highIMDB_rating
       * @param lowIMDB_rating
       * @return List of movies
       * @throws DalException
       * @throws IOException 
       */
      
      public List<Movie> movieSearch(String titleSearch, double highP_rating, double lowP_rating, double highIMDB_rating, double lowIMDB_rating, int categoryID) throws DalException, IOException
      {
         try ( Connection con = dbCon.getConnection())
        { 
         if(categoryID == 0)
         {
         String SQL = "SELECT DISTINCT Movie.*\n" +
                      "FROM CatMovie\n" +
                      "JOIN Movie as movie ON catmovie.MovieId = movie.id\n" +
                      "WHERE name LIKE ? AND p_rating <= ? AND p_rating >= ?\n" +
                      "AND imdb_rating <= ? AND imdb_rating >= ?";
         
                
         PreparedStatement ps = con.prepareStatement(SQL);
         
         ps.setString(1, "%" + titleSearch + "%");
         ps.setDouble(2, highP_rating);
         ps.setDouble(3, lowP_rating);
         ps.setDouble(4, highIMDB_rating);
         ps.setDouble(5, lowIMDB_rating);
         
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
                String cats = getCategoriesforMovie(id);
                
                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, lastview, imdb_link, cats);
                allMovies.add(mov);
            }
            return allMovies;
         } else
         {
         String SQL = "SELECT DISTINCT Movie.*\n" +
                      "FROM CatMovie\n" +
                      "JOIN Movie as movie ON catmovie.MovieId = movie.id\n" +
                      "WHERE CategoryId = ? AND name LIKE ? AND p_rating <= ? AND p_rating >= ?\n" +
                      "AND imdb_rating <= ? AND imdb_rating >= ?";   
         
                
         PreparedStatement ps = con.prepareStatement(SQL);
         
         ps.setInt(1, categoryID);
         ps.setString(2, "%" + titleSearch + "%");
         ps.setDouble(3, highP_rating);
         ps.setDouble(4, lowP_rating);
         ps.setDouble(5, highIMDB_rating);
         ps.setDouble(6, lowIMDB_rating);
         
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
                String cats = getCategoriesforMovie(id);
                
                Movie mov = new Movie(id, title, imdb_rating, p_rating, filelocation, lastview, imdb_link, cats);
                allMovies.add(mov);
            }
            return allMovies;
         }

         
         
         }
          catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
          
      }
}
