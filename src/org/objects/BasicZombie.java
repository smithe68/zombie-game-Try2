package org.objects;

import org.engine.CustomMathf;
import org.engine.ID;
import org.engine.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class BasicZombie extends GameObject
{
    public ZombieState state = ZombieState.Standing;

    private BufferedImage image;

    private GameObject player = Player.player;

    private float rotation = 0;

    private float horiAxis = 0;
    private float vertAxis = 0;

    public enum ZombieState
    {
        Standing,
        Hunting
    }

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
        double distance = x - player.x;

        if(distance < 150 && distance > -150)
        {
            state = ZombieState.Hunting;
            x = CustomMathf.Lerp(x, player.x, deltaTime / 2.5f);
            y = CustomMathf.Lerp(y, player.y, deltaTime / 2.5f);
        }
        else
        {
            state = ZombieState.Standing;
        }

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
        g2d.drawString(state.toString(), x, y + 15);
    }
}
