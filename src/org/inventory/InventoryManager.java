package org.inventory;

import java.util.ArrayList;

public class InventoryManager
{
    private static ArrayList<Item> items = new ArrayList<>();
    private static int maxSize = 9;

    public static void initialize() {
        ItemDatabase.initialize();
    }

    public static void addItem(Item item)
    {
        if(items.size() < 9) {
            items.add(item);
        }
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }
}
