/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import pmc2020.BE.Category;

/**
 *
 * @author Guruerne
 */
public class CategoryDAO
{

    private DatabaseConnector dbCon;
    
    /** 
     * makes new database connector
     * @throws IOException 
     */

    public CategoryDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /** 
     * method to get all categories as list
     * @return all categories
     * @throws DalException 
     */
    
    public List<Category> getAllCategories() throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Category;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Category> allCategories = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String catName = rs.getString("category");

                Category cat = new Category(id, catName);
                allCategories.add(cat);
            }
            return allCategories;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }

    }
    
    /** 
     * creates a category, with given data in the DB
     * @param category
     * @return category, with ID attached
     * @throws DalException 
     */

    public Category createCategory(String category) throws DalException
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "INSERT INTO Category (category) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, category);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);

                    Category cat = new Category(id, category);
                    return cat;
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
     * deletes a category from the DB
     * @param category
     * @throws DalException 
     */

    public void deleteCategory(Category category) throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            int id = category.getCategory_ID();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Category WHERE id=?");

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
     * updates a category in the DB, with given data
     * @param category
     * @throws DalException 
     */
    
    public void updateCategory(Category category) throws DalException
    {

        try ( Connection con = dbCon.getConnection())
        {
            int id = category.getCategory_ID();
            String catname = category.getCategory();

            PreparedStatement ps = con.prepareStatement("UPDATE Category SET category=? WHERE id=?");

            ps.setString(1, catname);
            ps.setInt(2, id);

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
     * Gets all categoies per movie as a list
     * @param movieid
     * @return all categories
     * @throws SQLException
     * @throws DalException 
     */

    public List<Category> getCategoryPerMovie(int movieid) throws SQLException, DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "SELECT DISTINCT Category.* FROM CatMovie as catmovie \n"
                    + "JOIN Movie as movie ON catmovie.MovieId = movie.id \n"
                    + "JOIN Category as category ON catmovie.CategoryId = category.id \n"
                    + "WHERE movie.id = " + movieid;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            ArrayList<Category> allCats = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String category = rs.getString("category");

                Category cat = new Category(id, category);
                allCats.add(cat);
            }
            return allCats;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }

    }



}
