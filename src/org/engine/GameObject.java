package org.engine;

import org.enums.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject
{
    public float posX, posY;
    public ID id;

    public boolean isSolid = true;

    public BufferedImage image = null;

    public GameObject(float x, float y, ID id)
    {
        this.posX = x;
        this.posY = y;
        this.id = id;
    }

    public void Update()
    {

    }

    public void Render(Graphics g)
    {
        if(image == null) return;

        g.drawImage(image, (int)posX, (int)posY, image.getWidth(), image.getHeight(), null);
    }

    public Rectangle getBounds()
    {
        Rectangle bounds;

        if(isSolid)
        {
            if(image != null)
            {
                bounds = new Rectangle((int)posX, (int)posY, image.getWidth(), image.getHeight());
            }
            else
            {
                bounds = null;
            }
        }
        else
        {
            bounds = null;
        }

        return bounds;
    }
}
