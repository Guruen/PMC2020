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
    private final SimpleIntegerProperty ID = new SimpleIntegerProperty();
    private final SimpleStringProperty Title = new SimpleStringProperty();
    private final SimpleDoubleProperty IMDB_Rating = new SimpleDoubleProperty();
    private final SimpleDoubleProperty Private_Rating = new SimpleDoubleProperty();
    private final SimpleStringProperty File_location = new SimpleStringProperty();
    private final SimpleStringProperty Last_Viewed = new SimpleStringProperty();
    private final SimpleStringProperty IMDB_Link = new SimpleStringProperty();
    
    /**
     * The method that defines what a movie is made up of, in our DB
     * @param id
     * @param title
     * @param imdb_rating
     * @param private_rating
     * @param file_location
     * @param last_viewed
     * @param imdb_link 
     */

    public Movie(int id, String title, double imdb_rating, double private_rating, String file_location, String last_viewed, String imdb_link)
    {
        setID(id);
        setTitle(title);
        setIMDB_Rating(imdb_rating);
        setPrivate_Rating(private_rating);
        setFile_location(file_location);
        setLast_Viewed(last_viewed);
        setIMDB_Link(imdb_link);
    }

    
    /**
     * Gets a movie ID from the DB
     * @return  Id of movie from DB
     */
    
  public final int getID()
  {
      return ID.get();
  }
  
  /**
   * Sets the movies ID in the DB to the selected value
   * @param id 
   */
  
  public final void setID(int id)
  {
      ID.set(id);
  }
  
  public SimpleIntegerProperty idProperty()
  {
      return ID;
  }
  
  /**
   * Gets the title of the chosen movie from the DB
   * @return movies title
   */

  public final String getTitle()
  {
      return Title.get();
  }
  
  /**
   * sets the title of the chosen movie to a new title
   * @param title 
   */
   
  public final void setTitle(String title)
  {
      Title.set(title);
  }
  
  public SimpleStringProperty titleProperty()
  {
      return Title;
  }
  
  /**
   * Gets the IMDB rating of the selected movie from the DB
   * @return IMDB rating of the selected movie
   */
    
  public final double getIMDB_Rating()
  {
      return IMDB_Rating.get();
  }
  
  /**
   * Sets the IMDB rating of a movie to a new value
   * @param i 
   */
  
  public final void setIMDB_Rating(double i)
  {
      IMDB_Rating.set(i);
  }
  
  public SimpleDoubleProperty imdb_ratingProperty()
  {
      return IMDB_Rating;
  }
  
  /**
   * Gets the users rating of a movie from the DB
   * @return Users private rating
   */
    
  public final double getPrivate_Rating()
  {
      return Private_Rating.get();
  }
  
  /**
   * Sets the movies user rating in the DB to a new value
   * @param p 
   */
    
  public final void setPrivate_Rating(double p)
  {
      Private_Rating.set(p);
  }
    
  public SimpleDoubleProperty private_ratingProperty()
  {
      return Private_Rating;
  }
  
  /**
   * Gets the file location of the selected entry from the DB
   * @return File path of the selected entry
   */
  
  public final String getFile_location()
  {
      return File_location.get();
  }
  
  /**
   * Sets the file location from the DB to a new path, that the user specifies
   * @param fl 
   */
    
  public final void setFile_location(String fl)
  {
      File_location.set(fl);
  }
    
  public SimpleStringProperty file_locationProperty()
  {
      return File_location;
  }
  
  /**
   * Gets the date when the entry was last accessed from the DB
   * @return last viewed date
   */
    
  public final String getLast_Viewed()
  {
      return Last_Viewed.get();
  }
  
  /**
   * sets the last viewed date to new date in the DB
   * @param lv 
   */
  
  public final void setLast_Viewed(String lv)
  {
      Last_Viewed.set(lv);
  }
  
  public SimpleStringProperty last_viewedProperty()
  {
      return Last_Viewed;
  }
  
  /**
   * Gets the IMDB link to the selected entry from the DB
   * @return link to the specific IMDB page
   */
  
  public final String getIMDB_Link()
  {
      return IMDB_Link.get();
  }
  
  /**
   * Sets the IMDB link in the DB to a new link
   * @param il 
   */
  
  public final void setIMDB_Link(String il)
  {
      IMDB_Link.set(il);
  }
  
  public SimpleStringProperty imdb_linkProperty()
  {
      return IMDB_Link;
  }
  
  /**
   * converts movie title to string
   * @return the title
   */

 
      @Override
    public String toString()
    {
        return Title.get();
    }
    
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
    
//    public Movie(int ID, String Title, double IMDB_Rating, double Private_rating, String File_location, String imdb_Link, String last_view)
//    {
//        this.ID = ID;
//        this.Title = Title;
//        this.IMDB_Rating = IMDB_Rating;
//        this.Private_rating = Private_rating;
//        this.File_location = File_location;
//        this.imdb_Link = imdb_Link;
//        this.Last_Viewed = last_view;
//    }
//
//    public Movie(int id, String title, double imdb_rating, String filelocation, String last_view, String imdb_link)
//    {
//        this.ID = id;
//        this.Title = title;
//        this.IMDB_Rating = imdb_rating;
//        this.File_location = filelocation;
//        this.imdb_Link = imdb_link;
//        this.Last_Viewed = last_view;
//    }
//
//    public int getID()
//    {
//        return ID;
//    }
//
//    public void setID(int ID)
//    {
//        this.ID = ID;
//    }
//
//    public String getTitle()
//    {
//        return Title;
//    }
//
//    public void setTitle(String Title)
//    {
//        this.Title = Title;
//    }
//
//    public double getIMDB_Rating()
//    {
//        return IMDB_Rating;
//    }
//
//    public void setIMDB_Rating(double IMDB_Rating)
//    {
//        this.IMDB_Rating = IMDB_Rating;
//    }
//
//    public double getPrivate_rating()
//    {
//        return Private_rating;
//    }
//
//    public void setPrivate_rating(double Private_rating)
//    {
//        this.Private_rating = Private_rating;
//    }
//
//    public String getLast_Viewed()
//    {
//        return Last_Viewed;
//    }
//
//    public void setLast_Viewed(String Last_Viewed)
//    {
//        this.Last_Viewed = Last_Viewed;
//    }
//
//    public String getFile_location()
//    {
//        return File_location;
//    }
//
//    public void setFile_location(String File_location)
//    {
//        this.File_location = File_location;
//    }
//       
//    public String getIMDB_Link()
//    {
//        return imdb_Link;
//    }
//
//    public void setIMDB_Link(String imdb_Link)
//    {
//        this.imdb_Link = imdb_Link;
//    }
//    

}
