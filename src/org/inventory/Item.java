package org.inventory;

public class Item
{
    public String name;
    public String desc;
    public int id;

    public String image;

    public void use()
    {
        System.out.println("Used " + name);
    }
}
