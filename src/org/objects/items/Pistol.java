package org.objects.items;

import org.engine.logic.Level;
import org.inventory.Item;
import org.objects.Zombie;

public class Pistol extends Item
{
    public Pistol()
    {
        name = "Pistol";
        desc = "Semi Automatic Pistol";
        id = 1;

        image = "Pistol_Side.png";
    }

    @Override
    public void use()
    {
        super.use();

        Level.instantiate(new Zombie(0, 0));
    }
}
