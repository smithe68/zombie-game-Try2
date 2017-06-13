package org.ui;

import java.awt.event.KeyEvent;
import org.enums.ID;
import org.input.Input;
import org.objects.GameObject;
import org.objects.Player;

import java.awt.*;

public class ActionBar extends GameObject
{
    public Player player = Player.player;
    public Inventory inv = Player.inv;

    public Item selectedItem;
    public int selectedIndex;
    public int selectedAmount;

    public ActionBar(int x, int y, ID id)
    {
        super(x, y, id);
    }

    public void tick(float deltaTime)
    {
        if(Input.GetKeyDown(KeyEvent.VK_1))
        {
            ItemAction(0, inv.items.get(0).itemEffect);
            selectedItem = inv.items.get(0);
            selectedIndex = 0;
            selectedAmount = inv.itemAmounts.get(0);
        }

        if(Input.GetKeyDown(KeyEvent.VK_2))
        {
            ItemAction(1, inv.items.get(1).itemEffect);
            selectedItem = inv.items.get(1);
            selectedIndex = 1;
            selectedAmount = inv.itemAmounts.get(1);
        }

        if(Input.GetKeyDown(KeyEvent.VK_3))
        {
            ItemAction(2, inv.items.get(2).itemEffect);
            selectedItem = inv.items.get(2);
            selectedIndex = 2;
            selectedAmount = inv.itemAmounts.get(2);
        }

        if(Input.GetKeyDown(KeyEvent.VK_4))
        {
            ItemAction(3, inv.items.get(3).itemEffect);
            selectedItem = inv.items.get(3);
            selectedIndex = 3;
            selectedAmount = inv.itemAmounts.get(3);
        }

        if(Input.GetKeyDown(KeyEvent.VK_5))
        {
            ItemAction(4, inv.items.get(4).itemEffect);
            selectedItem = inv.items.get(4);
            selectedIndex = 4;
            selectedAmount = inv.itemAmounts.get(4);
        }
    }

    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.red);

        g2.drawRect(20, 220, 20, 20);
        g2.drawImage(inv.items.get(0).itemSprite, 14, 215, null);

        g2.drawRect(50, 220, 20, 20);
        g2.drawImage(inv.items.get(1).itemSprite, 44, 215, null);

        g2.drawRect(80, 220, 20, 20);
        g2.drawImage(inv.items.get(2).itemSprite, 74, 215, null);

        g2.drawRect(110, 220, 20, 20);
        g2.drawImage(inv.items.get(3).itemSprite, 104, 215, null);

        g2.drawRect(140, 220, 20, 20);
        g2.drawImage(inv.items.get(4).itemSprite, 134, 215, null);
    }

    public Rectangle getBounds()
    {
        return null;
    }

    public void ItemAction(int itemID, Item.ItemEffect effect)
    {
        switch(effect)
        {
            case Heal:

                break;

            case Hurt:

                break;

            case Equip:
                player.equip = inv.items.get(itemID).itemSprite;
                break;

            case Nothing:
                System.out.println("Cannot use this Item");
                break;
        }
    }
}
