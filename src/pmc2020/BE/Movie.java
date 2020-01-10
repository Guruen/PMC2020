/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BE;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pmc2020.DAL.CategoryDAO;
import pmc2020.DAL.DalException;

/**
 *
 * @author Guruerne
 */
public class Movie
{

    private int ID;
    private String Title;
    private double IMDB_Rating;
    private double Private_Rating;
    private String File_location;
    private String Last_Viewed;
    private String IMDB_Link;
    private String Categories;
    

    
    /**
     * 
     * @param ID
     * @param Title
     * @param IMDB_Rating
     * @param Private_rating
     * @param File_location
     * @param imdb_Link
     * @param last_view - Format is YYYY-MM-DD
     */
    
    public Movie(int id, String title, double imdb_rating, double private_rating, String file_location, String last_viewed, String imdb_link, String categories)
    {
        this.ID = id;
        this.Title = title;
        this.IMDB_Rating = imdb_rating;
        this.Private_Rating = private_rating;
        this.File_location = file_location;
        this.IMDB_Link = imdb_link;
        this.Last_Viewed = last_viewed;
        this.Categories = categories;
        
    }

    public Movie(int id, String title, double imdb_rating, String filelocation, String last_view, String imdb_link)
    {
        this.ID = id;
        this.Title = title;
        this.IMDB_Rating = imdb_rating;
        this.File_location = filelocation;
        this.IMDB_Link = imdb_link;
        this.Last_Viewed = last_view;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String Title)
    {
        this.Title = Title;
    }

    public double getIMDB_Rating()
    {
        return IMDB_Rating;
    }

    public void setIMDB_Rating(double IMDB_Rating)
    {
        this.IMDB_Rating = IMDB_Rating;
    }

    public double getPrivate_Rating()
    {
        return Private_Rating;
    }

    public void setPrivate_Rating(double Private_rating)
    {
        this.Private_Rating = Private_rating;
    }

    public String getLast_Viewed()
    {
        return Last_Viewed;
    }

    public void setLast_Viewed(String Last_Viewed)
    {
        this.Last_Viewed = Last_Viewed;
    }

    public String getFile_location()
    {
        return File_location;
    }

    public void setFile_location(String File_location)
    {
        this.File_location = File_location;
    }
       
    public String getIMDB_Link()
    {
        return IMDB_Link;
    }

    public void setIMDB_Link(String imdb_Link)
    {
        this.IMDB_Link = imdb_Link;
    }
    
    public String getCategories()
    {
        return Categories;
    }
         
    public void setCategories(String categories)
    {
        this.Categories = categories;
    }
          
      @Override
    public String toString()
    {
        return Title;
    }
}
