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
    public ZombieState state = ZombieState.Standing;

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

        if(distance < 150 && distance > -150)
        {
            // Rotate toward the Player
            rotation = (float)Math.atan2(posDiff.y, posDiff.x);

            state = ZombieState.Hunting;

            posX = CustomMath.Lerp(posX, player.posX, Renderer.deltaTime / 2);
            posY = CustomMath.Lerp(posY, player.posY, Renderer.deltaTime / 2);
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
        for(int i = 0; i < Level.objects.size(); i++)
        {
            GameObject tempObject = Level.objects.get(i);

            if (tempObject.id == ID.Bullet)
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    Sound.PlaySound("/resources/sounds/Hit_01.wav", -20, false);
                    isHit = true;
                    hud.HEALTH -= 10f;
                    Game.Destroy(tempObject);
                }
            }
        }
    }

    public void Render(Graphics g)
    {
        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = g2d.getTransform();

        centerX = (int) posX + image.getWidth() / 2;
        centerY = (int) posY + image.getHeight() / 2;

        g2d.drawRect((int) posX, (int) posY, 32, 32);

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
    }
}
