package org.objects;

import org.engine.*;
import org.enums.ID;
import org.ui.HUD;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BasicZombie extends GameObject
{
    // Current State of Zombie AI
    public ZombieState state = ZombieState.Standing;

    private GameObject player = Player.player;

    private BufferedImage hit;
    private boolean isHit;

    private GameObject hudObj;
    private HUD hud;

    private float lastRotation = 0;
    private float rotation = 0;

    private double centerX = 0;
    private double centerY = 0;

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
            hit = Renderer.LoadImage("/resources/sprites/BasicZombie_Hit.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Create a Health Bar for Zombie
        hud = new HUD(x, y, ID.HUD, 20, 5);
        hudObj = Game.Instantiate(hud);
    }

    public Rectangle getBounds()
    {
        // Change Collider based on Rotation
        AffineTransform transform = new AffineTransform();
        Rectangle rect = new Rectangle((int) posX,
                (int) posY - image.getHeight(), image.getWidth(), image.getHeight());
        transform.rotate(rotation, rect.width / 2, rect.height / 2);
        return rect;
    }

    public void Update()
    {
        // UI
        if(hudObj != null)
        {
            hudObj.posX = posX + 5;
            hudObj.posY = posY - 10;
        }

        // Movement
        double distance = posX - player.posX;

        Point posDiff = (new Point((int)(player.posX - posX), (int)(player.posY - posY)));

        // Follow the Player in Certain Distance
        if(distance < 150 && distance > -150)
        {
            // Rotate toward the Player
            rotation = (float)Math.atan2(posDiff.y, posDiff.x);

            state = ZombieState.Hunting;

            // Lerp Position to Player
            posX = CustomMath.Lerp(posX, player.posX, Renderer.deltaTime / 2);
            posY = CustomMath.Lerp(posY, player.posY, Renderer.deltaTime / 2);
        }
        else
        {
            state = ZombieState.Standing;
            lastRotation = rotation;
        }

        Collision();

        // Destroy if Health is Zero
        if(hud.HEALTH <= 0)
        {
            Game.Destroy(hud);
            Game.Destroy(this);
        }
    }

    public void Collision()
    {
        // Check for Collision with Bullet
        for(int i = 0; i < Level.objects.size(); i++)
        {
            GameObject tempObject = Level.objects.get(i);

            if (tempObject.id == ID.Bullet)
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    // Do when Hit by Bullet
                    Sound.PlaySound("/resources/sounds/Hit_01.wav", -20, false);
                    isHit = true;
                    hud.HEALTH -= 15f;
                }
            }
        }
    }

    public void Render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        // Get the Transform of the Zombie
        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
        AffineTransform transform = g2d.getTransform();

        // Get the Center of the Zombie
        centerX = (int) posX + image.getWidth() / 2;
        centerY = (int) posY + image.getHeight() / 2;

        // Rotate based on Zombie State
        if(state == ZombieState.Hunting)
        {
            g2d.rotate(rotation, centerX, centerY);
        }

        if(state == ZombieState.Standing)
        {
            g2d.rotate(lastRotation, centerX, centerY);
        }

        if(isHit)
        {
            // Draw if Hit
            g2d.drawImage(hit, at, null);
            isHit = false;
        }
        else
        {
            // Draw the Normal Sprite
            g2d.drawImage(image, at, null);
        }

        g2d.setTransform(transform);
    }
}
