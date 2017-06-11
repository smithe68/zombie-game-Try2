package org.objects;
import org.engine.Handler;
import org.engine.ID;
import org.engine.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by evan on 6/10/2017.
 */
public class YSpeedFollower extends GameObject
{

    private Handler handler;
    private BufferedImage image;
    private GameObject player;

    public YSpeedFollower(int x , int y, ID id, GameObject player, Handler handler)
    {
        super(x, y,id);

        this.player = player;
        this.handler = handler;

        try
        {
            // Get the Sprite for the Follower
            image = handler.LoadImage("/resources/sprites/Zombie.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y,45,45);
    }

    public void tick()
    {
        float diffX = x - player.GetX();
        float diffY = y - player.GetY();

        float distance = (float)(Math.sqrt((diffX * diffX) + (diffY * diffY)));

        velX = (-1.0f / distance) * diffX * 2.3f;
        velY = (-1.0f / distance) * diffY * 4.5f;

        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.screenHeight -50 )
        {
            velY *= -1;
        }

        if(x <= 0 || x >= Main.screenWidth - 32)
        {
            velX *= -1;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(image, (int)x,(int) y, 64, 64, null);
    }

}
