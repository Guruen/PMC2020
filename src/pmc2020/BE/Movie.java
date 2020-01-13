/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BE;



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
 * This is the movie constructor that defines what a movie must be
 * @param id
 * @param title
 * @param imdb_rating
 * @param private_rating
 * @param file_location
 * @param last_viewed
 * @param imdb_link
 * @param categories 
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
    
    /**
     * This is a polymorphed version of the other movie constructor
     * @param id
     * @param title
     * @param imdb_rating
     * @param filelocation
     * @param last_view
     * @param imdb_link 
     */

    public Movie(int id, String title, double imdb_rating, String filelocation, String last_view, String imdb_link)
    {
        this.ID = id;
        this.Title = title;
        this.IMDB_Rating = imdb_rating;
        this.File_location = filelocation;
        this.IMDB_Link = imdb_link;
        this.Last_Viewed = last_view;
    }
    
    /**
     * Gets the ID of the movie from DB
     * @return ID
     */

    public int getID()
    {
        return ID;
    }
    
    /**
     * Sets the id of the selected movie in the DB
     * @param ID 
     */

    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    /**
     * Gets the title of the movie from DB
     * @return Title
     */

    public String getTitle()
    {
        return Title;
    }
    
    /**
     * Sets the title of the selected movie to something new, that is specified by the user
     * @param Title 
     */

    public void setTitle(String Title)
    {
        this.Title = Title;
    }
    
    /**
     * Gets the IMDB rating of the selected movie from DB
     * @return IMDB rating
     */

    public double getIMDB_Rating()
    {
        return IMDB_Rating;
    }
    
    /**
     * Sets the IMDB rating of the movie in the DB to something new
     * @param IMDB_Rating 
     */

    public void setIMDB_Rating(double IMDB_Rating)
    {
        this.IMDB_Rating = IMDB_Rating;
    }
    
    /**
     * Gets the users own rating to the selected movie from the DB
     * @return User rating
     */

    public double getPrivate_Rating()
    {
        return Private_Rating;
    }

    /**
     * Sets the users rating to something new in the DB
     * @param Private_rating 
     */
    
    public void setPrivate_Rating(double Private_rating)
    {
        this.Private_Rating = Private_rating;
    }
    
    /**
     * Gets the date when the user last viewed the selected entry from the DB
     * @return Last viewed date
     */

    public String getLast_Viewed()
    {
        return Last_Viewed;
    }
    
    /**
     * Sets the date when the user last viewed the movie to the current date in the DB
     * @param Last_Viewed 
     */

    public void setLast_Viewed(String Last_Viewed)
    {
        this.Last_Viewed = Last_Viewed;
    }

    /**
     * Gets the file path to the selected entry from the DB
     * @return File location
     */
    
    public String getFile_location()
    {
        return File_location;
    }
    
    /**
     * Sets the file location to the selected entry to something new in the DB
     * @param File_location 
     */

    public void setFile_location(String File_location)
    {
        this.File_location = File_location;
    }
    
    /**
     * Gets the IMDB link to the selected movie from the DB
     * @return IMDB Link
     */
       
    public String getIMDB_Link()
    {
        return IMDB_Link;
    }
    
    /**
     * Sets the IMDB link in the DB to something new in the DB
     * @param imdb_Link 
     */

    public void setIMDB_Link(String imdb_Link)
    {
        this.IMDB_Link = imdb_Link;
    }
    
    /**
     * Gets the categories from the DB
     * @return Categories
     */
    
    public String getCategories()
    {
        return Categories;
    }
    
    /**
     * Sets the category in the DB to sometghing new and saves this in the DB
     * @param categories 
     */
         
    public void setCategories(String categories)
    {
        this.Categories = categories;
    }
    
    /**
     * Sets title to string and returns this as a string
     * @return Title as string
     */
          
      @Override
    public String toString()
    {
        return Title;
    }
}
