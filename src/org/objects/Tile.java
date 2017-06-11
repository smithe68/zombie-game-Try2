package org.objects;

import org.engine.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends GameObject
{
    public Tile(int x, int y, ID id, BufferedImage sprite)
    {
        super(x, y, id);

        tileSprite = sprite;
    }

    public BufferedImage tileSprite;

    public void tick(float deltaTime)
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(tileSprite, (int)x,(int) y, 96, 96, null);
    }




    @Override
    public Rectangle getBounds() {
        return null;
    }
}
