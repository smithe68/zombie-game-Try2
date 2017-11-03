package org.objects.items;

import org.engine.logic.*;
import org.inventory.Item;
import org.objects.*;

public class Pistol extends Item
{
    public Pistol(GameObject parent)
    {
        super(parent);

        name = "Pistol";
        desc = "Semi Automatic Pistol";
        id = 1;

        image = "Pistol_Side.png";
    }

    @Override
    public void equip()
    {
        super.equip();

        if(parent.tag.equals("Player"))
        {
            Player player = (Player)parent;

            if(player.getEquippedItem() == null)
                player.setEquippedItem(this);
        }
    }

    @Override
    public void use()
    {
        if(parent.tag.equals("Player"))
        {
            Player player = (Player)parent;
            if(player.getEquippedItem() == this) {
                Level.instantiate(new Bullet(player.x, player.y));
            }
        }
    }
}
