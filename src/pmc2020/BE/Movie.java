/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.BE;

/**
 *
 * @author CSnit
 */
public class Movie
{
    private int ID;
    private String Title;
    private double IMDB_Rating;
    private double Private_rating;
    private String Last_Viewed;
    private String File_location;
    private String imdb_Link;

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
    
    public Movie(int ID, String Title, double IMDB_Rating, double Private_rating, String File_location, String imdb_Link, String last_view)
    {
        this.ID = ID;
        this.Title = Title;
        this.IMDB_Rating = IMDB_Rating;
        this.Private_rating = Private_rating;
        this.File_location = File_location;
        this.imdb_Link = imdb_Link;
        this.Last_Viewed = last_view;
    }

    public Movie(int id, String title, double imdb_rating, String filelocation, String last_view, String imdb_link)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public double getPrivate_rating()
    {
        return Private_rating;
    }

    public void setPrivate_rating(double Private_rating)
    {
        this.Private_rating = Private_rating;
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
        return imdb_Link;
    }

    public void setIMDB_Link(String imdb_Link)
    {
        this.imdb_Link = imdb_Link;
    }
    
    @Override
    public String toString()
    {
        return Title;
    }
}
