package org.objects.items;

import org.inventory.Item;

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
    }
}
