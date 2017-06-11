package org.objects;

import org.engine.ID;
import org.engine.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Follower extends GameObject
{
    private BufferedImage image;

    private GameObject player = Player.player;

    public Follower(int x , int y, ID id)
    {
        super(x, y, id);

        try
        {
            // Get the Sprite for the Follower
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
        float diffX = x - player.GetX();
        float diffY = y - player.GetY();

        float distance = (float)(Math.sqrt((diffX * diffX) + (diffY * diffY)));

        velX = (-1.0f / distance) * diffX * 2;
        velY = (-1.0f / distance) * diffY * 2;

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
        g.drawImage(image, (int)x,(int) y, 64, 64, null);
    }
}
