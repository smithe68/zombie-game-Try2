package org.ui;

import org.engine.Game;
import org.objects.GameObject;
import org.objects.Player;

import java.util.ArrayList;

public class Inventory
{
    public Player player = Player.player;

    public int inventorySize = 10;

    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Integer> itemAmounts = new ArrayList<>();

    public Database data = player.data;

    public void Initialize()
    {
        for(int i = 0; i < inventorySize; i++)
        {
            items.add(data.empty);
            itemAmounts.add(0);
        }
    }

    public void AddToInventory(Item item, int amount, GameObject object)
    {
        for(int i = 0; i < inventorySize; i++)
        {
            if(item.itemID == items.get(i).itemID)
            {
                int combined = itemAmounts.get(i) + amount;

                if(combined < items.get(i).stackSize)
                {
                    itemAmounts.set(i, itemAmounts.get(i) + amount);
                    Game.Destroy(object);
                }
            }

            if(items.get(i).itemID == 0)
            {
                items.set(i, item);
                itemAmounts.set(i, amount);
                Game.Destroy(object);
                break;
            }
        }

        for(int i = 0; i < inventorySize; i++)
        {
            System.out.println(items.get(i).itemName + ", " + itemAmounts.get(i).toString());
        }
    }
}
