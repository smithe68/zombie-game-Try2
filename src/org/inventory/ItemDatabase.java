package org.inventory;

import org.engine.logic.Level;
import org.objects.*;
import org.objects.items.Pistol;

import java.util.ArrayList;

public class ItemDatabase
{
    private static ArrayList<Item> items = new ArrayList<>();

    public static void initialize()
    {
        Player player = (Player)Level.findObject("Player");
        System.out.println(player.health);

        items.add(new Item(player));
        items.add(new Pistol(player));
    }

    public static Item getItem(int index) {
        return items.get(index);
    }
}
