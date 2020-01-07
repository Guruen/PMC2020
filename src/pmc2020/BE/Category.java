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
public class Category
{
    private int category_ID;
    private String category;

    public Category(int category_ID, String category)
    {
        this.category_ID = category_ID;
        this.category = category;
    }

    public int getCategory_ID()
    {
        return category_ID;
    }

    public void setCategory_ID(int category_ID)
    {
        this.category_ID = category_ID;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    @Override
    public String toString()
    {
        return category;
    }
}
