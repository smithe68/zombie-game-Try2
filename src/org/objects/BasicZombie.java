package org.objects;

import org.engine.ID;
import org.engine.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BasicZombie extends GameObject
{
    private BufferedImage image;
    private GameObject player;

    public BasicZombie(int x , int y, ID id, GameObject player)
    {
        super(x, y, id);

        this.player = player;

        velX += 1;
        velY += 1;

        try
        {
            // Get the Sprite for the Zombie
            image = Renderer.LoadImage("/resources/sprites/Zombie.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y,64,64);
    }

    public void tick(float deltaTime)
    {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Renderer.gameHeight -50 )
        {
            velY *= -1;
        }

        if(x <= 0 || x >= Renderer.gameWidth - 32)
        {
            velX *= -1;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, 64, 64, null);
    }
}
