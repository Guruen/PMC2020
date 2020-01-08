/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BE;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author CSnit
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

    
  public final int getID()
  {
      return ID.get();
  }
  
  public final void setID(int id)
  {
      ID.set(id);
  }
  
  public SimpleIntegerProperty idProperty()
  {
      return ID;
  }

  public final String getTitle()
  {
      return Title.get();
  }
   
  public final void setTitle(String title)
  {
      Title.set(title);
  }
  
  public SimpleStringProperty titleProperty()
  {
      return Title;
  }
    
  public final double getIMDB_Rating()
  {
      return IMDB_Rating.get();
  }
  
  public final void setIMDB_Rating(double i)
  {
      IMDB_Rating.set(i);
  }
  
  public SimpleDoubleProperty imdb_ratingProperty()
  {
      return IMDB_Rating;
  }
    
  public final double getPrivate_Rating()
  {
      return Private_Rating.get();
  }
    
  public final void setPrivate_Rating(double p)
  {
      Private_Rating.set(p);
  }
    
  public SimpleDoubleProperty private_ratingProperty()
  {
      return Private_Rating;
  }
  
  public final String getFile_location()
  {
      return File_location.get();
  }
    
  public final void setFile_location(String fl)
  {
      File_location.set(fl);
  }
    
  public SimpleStringProperty file_locationProperty()
  {
      return File_location;
  }
    
  public final String getLast_Viewed()
  {
      return Last_Viewed.get();
  }
  
  public final void setLast_Viewed(String lv)
  {
      Last_Viewed.set(lv);
  }
  
  public SimpleStringProperty last_viewedProperty()
  {
      return Last_Viewed;
  }
  
  public final String getIMDB_Link()
  {
      return IMDB_Link.get();
  }
  
  public final void setIMDB_Link(String il)
  {
      IMDB_Link.set(il);
  }
  
  public SimpleStringProperty imdb_linkProperty()
  {
      return IMDB_Link;
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
//    @Override
//    public String toString()
//    {
//        return Title;
//    }
}
