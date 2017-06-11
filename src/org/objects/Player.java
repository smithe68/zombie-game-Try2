package org.objects;

import org.engine.ID;
import org.engine.Renderer;
import org.input.Input;
import org.ui.HUD;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject
{
    public static Player player;

    private BufferedImage image;

    public float speed = 2;

    public Player(int x, int y, ID id)
    {
        super(x, y, id);

        player = this;

        try
        {
            // Get the Sprite for the Player
            image = Renderer.LoadImage("/resources/sprites/PlayerDude.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,64,64);
    }

    public void tick(float deltaTime)
    {
        x+= velX;
        y += velY;

        x = Renderer.clamp(x,0, Renderer.gameWidth +761);
        y = Renderer.clamp(y,0, Renderer.gameHeight +531);

        // Move Player Left
        if(Input.GetKey(KeyEvent.VK_A))
        {
            x -= speed;
        }

        // Move Player Right
        if(Input.GetKey(KeyEvent.VK_D))
        {
            x += speed;
        }

        // Move Player Up
        if(Input.GetKey(KeyEvent.VK_W))
        {
            y -= speed;
        }

        // Move Player Down
        if(Input.GetKey(KeyEvent.VK_S))
        {
            y += speed;
        }

        collision();
    }

    private void collision()
    {
        for(int i = 0; i < World.gameObjects.size(); i++)
        {
            GameObject tempObject = World.gameObjects.get(i);

            if (tempObject.GetID() == ID.BasicZombie || tempObject.GetID() == ID.Follower)
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
            g.drawImage(image, (int)x, (int)y, 32, 32, null);
        }
    }
}
