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

    public static void main(String[] args) throws DalException, IOException, SQLException
    {
        CategoryDAO catDAO = new CategoryDAO();
        
        List<Category> allCats = catDAO.getAllCategories();
        for (Category allCat : allCats)
        {
            System.out.println(allCat + " ID:" + allCat.getCategory_ID());
        }
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
                String catName = rs.getString("categoryname");

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

    public Category createCategory(String category)
    {

        return null;
    }

    public void deleteCategory(Category category)
    {

    }

    public Category updateCategory(Category category)
    {

        return null;
    }
}
