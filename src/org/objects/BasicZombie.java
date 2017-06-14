package org.objects;

import org.engine.CustomMathf;
import org.engine.Game;
import org.enums.ID;
import org.engine.Renderer;
import org.ui.HUD;
import org.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BasicZombie extends GameObject
{
    public ZombieState state = ZombieState.Standing;

    private BufferedImage image;
    private BufferedImage hit;

    private GameObject player = Player.player;

    private GameObject hudObj;
    private HUD hud;

    private float rotation = 0;
    private float lastRotation = 0;

    private boolean isHit;

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

        hud = new HUD(x, y, ID.HUD, 20, 5);
        hudObj = Game.Instantiate(hud);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick(float deltaTime)
    {
        // UI

        if(hudObj != null)
        {
            hudObj.x = x + 5;
            hudObj.y = y - 10;
        }

        // Movement

        double distance = x - player.x;

        Point posDiff = (new Point((int)(player.x - x), (int)(player.y - y)));

        if(distance < 150 && distance > -150)
        {
            // Rotate toward the Player
            rotation = (float)Math.atan2(posDiff.y, posDiff.x);

            state = ZombieState.Hunting;

            x = CustomMathf.Lerp(x, player.x, deltaTime / 2);
            y = CustomMathf.Lerp(y, player.y, deltaTime / 2);
        }
        else
        {
            state = ZombieState.Standing;
            lastRotation = rotation;
        }

        Collision();

        if(hud.HEALTH <= 0)
        {
            Game.Destroy(hud);
            Game.Destroy(this);
        }
    }

    public void Collision()
    {
        for(int i = 0; i < World.gameObjects.size(); i++)
        {
            GameObject tempObject = World.gameObjects.get(i);

            if (tempObject.GetID() == ID.Speeder)
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    isHit = true;
                    hud.HEALTH -= 10f;
                    Game.Destroy(tempObject);
                }
            }
        }
    }

    public void render(Graphics g)
    {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = g2d.getTransform();

        centerX = (int)x + image.getWidth() / 2;
        centerY = (int)y + image.getHeight() / 2;

        if(state == ZombieState.Hunting)
        {
            g2d.rotate(rotation, centerX, centerY);
        }

        if(state == ZombieState.Standing)
        {
            g2d.rotate(lastRotation, centerX, centerY);
        }

        // Draw the Sprite
        if(isHit)
        {
            g2d.drawImage(hit, at, null);
            isHit = false;
        }
        else
        {
            g2d.drawImage(image, at, null);
        }

        g2d.setTransform(transform);

        g2d.drawRect((int)x, (int)y, 32, 32);
    }
}
