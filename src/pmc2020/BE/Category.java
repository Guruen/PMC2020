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

public class Category
{
    private int category_ID;
    private String category;
    
 /** 
  * Category defines the category that is written to the DB
  * @param category_ID The id of the category
  * @param category The name of the category
  */
    
    public Category(int category_ID, String category)
    {
        this.category_ID = category_ID;
        this.category = category;
    }

    /** 
     * Gets the category id from the DB
     * @return category_ID
     */
    
    public int getCategory_ID()
    {
        return category_ID;
    }

    /** 
     * Sets the id of the category in the DB
     * @param category_ID
     */
    
    public void setCategory_ID(int category_ID)
    {
        this.category_ID = category_ID;
    }
    
    /** 
     * Gets the category from the DB
     * @return category
     */

    public String getCategory()
    {
        return category;
    }
    
    /** 
     * Sets the category in the DB
     * @param category 
     */

    public void setCategory(String category)
    {
        this.category = category;
    }
    
    /** 
     * Converts the category to String
     * @return category
     */
    
    @Override
    public String toString()
    {
        return category;
    }
}
