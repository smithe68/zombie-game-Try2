package org.objects;

import org.engine.Handler;
import org.engine.ID;
import org.engine.Main;
import org.ui.HUD;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject
{
    private BufferedImage image;

    private Handler handler;

    public Player(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;

        try
        {
            // Get the Sprite for the Player
            image = handler.LoadImage("/resources/sprites/PlayerDude.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,45,45);
    }

    public void tick()
    {
        x+= velX;
        y += velY;

        x = Main.clamp(x,0,Main.WIDTH+761);
        y = Main.clamp(y,0,Main.HEIGHT+531);

        collision();
    }

    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.GetID() == ID.BasicZombie || tempObject.GetID() == ID.Follower ||tempObject.GetID() == ID.XSpeedFollower ||tempObject.GetID() == ID.YSpeedFollower || tempObject.GetID() == ID.Speeder )
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH -= 2;


                }
            }
        }
    }

    public void render(Graphics g)
    {
        if (id == ID.Player)
        {
            // Draw the Player's Sprite
            g.drawImage(image, (int)x, (int)y, 64, 64, null);
        }
    }
}
