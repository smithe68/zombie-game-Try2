package org.inventory;

import org.engine.portation.SpriteLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InventoryManager
{
    public static final int MAX_SIZE = 4;

    public static ArrayList<BufferedImage> itemImages = new ArrayList<>();

    private static ArrayList<Item> items = new ArrayList<>();

    public static void initialize() {
        ItemDatabase.initialize();
    }

    public static boolean addItem(Item item)
    {
        if(items.size() < MAX_SIZE)
        {
            items.add(item);
            itemImages.add(SpriteLoader.getSprite(item.image));
            return true;
        }
        else
            return false;
    }

    public static void removeItem(Item item)
    {
        int index = items.indexOf(item);
        items.remove(index);
        itemImages.remove(index);
    }

    public static void equipItem(int index) {
        items.get(index).equip();
    }

    public static boolean indicesOK(int index) {
        return index < items.size();
    }
}
