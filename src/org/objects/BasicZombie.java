package org.objects;

import org.engine.ID;
import org.engine.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BasicZombie extends GameObject
{
    private BufferedImage image;

    private GameObject player = Player.player;

    private float rotation = 0;

    private float horiAxis = 0;
    private float vertAxis = 0;

    public BasicZombie(int x , int y, ID id)
    {
        super(x, y, id);

        try
        {
            // Get the Sprite for the Follower
            image = Renderer.LoadImage("/resources/sprites/BasicZombie.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y,image.getWidth() / 2,image.getHeight() / 2);
    }

    public void tick(float deltaTime)
    {
        x = Renderer.Lerp(x, player.x, 2 * deltaTime);
        y = Renderer.Lerp(y, player.y, 2 * deltaTime);

        Point distToPlayer = (new Point((int)player.x - (int)x, (int)player.y - (int)y));

        // Rotate toward the Player
        rotation = (float)Math.atan2(distToPlayer.y, distToPlayer.x);
    }

    public void render(Graphics g)
    {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(rotation, image.getWidth() / 2, image.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the Player's Sprite
        g2d.drawImage(image, at, null);
    }
}
