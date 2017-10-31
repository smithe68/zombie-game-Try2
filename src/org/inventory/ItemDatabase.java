package org.inventory;

import org.objects.items.Pistol;

import java.util.ArrayList;

public class ItemDatabase
{
    private static ArrayList<Item> items = new ArrayList<>();

    public static void initialize()
    {
        items.add(new Item());
        items.add(new Pistol());
    }

    public static Item getItem(int index) {
        return items.get(index);
    }
}
