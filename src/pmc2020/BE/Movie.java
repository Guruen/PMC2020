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
    private int Last_Viewed;
    private String File_location;

    public Movie(int ID, String Title, double IMDB_Rating, double Private_rating, String File_location)
    {
        this.ID = ID;
        this.Title = Title;
        this.IMDB_Rating = IMDB_Rating;
        this.Private_rating = Private_rating;
        this.File_location = File_location;
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

    public double getIMBV_Rating()
    {
        return IMDB_Rating;
    }

    public void setIMBV_Rating(double IMBV_Rating)
    {
        this.IMDB_Rating = IMBV_Rating;
    }

    public double getPrivate_rating()
    {
        return Private_rating;
    }

    public void setPrivate_rating(double Private_rating)
    {
        this.Private_rating = Private_rating;
    }

    public int getLast_Viewed()
    {
        return Last_Viewed;
    }

    public void setLast_Viewed(int Last_Viewed)
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
    
}
