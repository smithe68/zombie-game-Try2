package org.objects;

import org.engine.logic.*;
import org.engine.portation.SpriteLoader;
import org.inventory.InventoryManager;
import org.inventory.Item;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Pickup extends GameObject
{
    public Item item;

    public Pickup(double x, double y)
    {
        super(x, y);

        width = 32;
        height = 32;

        if(image != null)
            image = SpriteLoader.getSprite(item.image);

        isDynamic = true;
        isTrigger = true;
    }

    @Override
    public void onCollision(GameObject g)
    {
        if(g.tag.equals("Player"))
        {
            InventoryManager.addItem(item);
            Level.destroy(this);
        }
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform transform = g.getTransform();

        rotation += 1;

        if(rotation > 360)
            rotation = 0;

        g.rotate(Math.toRadians(rotation), posX + width / 2, posY + height / 2);
        g.drawImage(image, (int)posX, (int)posY, width, height, null);
        g.setTransform(transform);
    }
}
