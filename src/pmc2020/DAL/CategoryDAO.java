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
 * @author CSnit
 */
public class CategoryDAO
{

    private DatabaseConnector dbCon;

    public CategoryDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

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
}
