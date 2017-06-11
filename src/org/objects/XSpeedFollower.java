package org.objects;
import org.engine.ID;
import org.engine.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class XSpeedFollower extends GameObject
{
    private BufferedImage image;
    private GameObject player = Player.player;

    public XSpeedFollower(int x , int y, ID id)
    {
        super(x, y,id);

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
        return new Rectangle((int)x, (int)y,30,30);
    }

    public void tick(float deltaTime)
    {
        x = Renderer.Lerp(x, player.x, 3 * deltaTime);
        y = Renderer.Lerp(y, player.y, 2 * deltaTime);

        if(y <= 0 || y >= Renderer.gameHeight -50 )
        {
            velY *= -1;
        }

        if(x <= 0 || x >= Renderer.gameHeight - 32)
        {
            velX *= -1;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(image, (int)x,(int) y, 64, 64, null);
    }
}
