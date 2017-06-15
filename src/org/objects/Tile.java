package org.objects;

import org.engine.GameObject;
import org.engine.Renderer;
import org.enums.ID;

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
        //KEK
    }

    public void Render(Graphics g)
    {
        g.drawImage(tileSprite, (int)(posX - tileSprite.getWidth() / 2) - (int)Renderer.camPosX,
        (int)(posY - tileSprite.getHeight() / 2) - (int)Renderer.camPosY, 96, 96, null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
