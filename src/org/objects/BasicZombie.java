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

    private GameObject player = Player.player;

    private GameObject hudObj;
    private HUD hud;

    private float rotation = 0;
    private float lastRotation = 0;

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

        hud = new HUD(x, y, ID.HUD, 20, 5);
        hudObj = Game.Instantiate(hud);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y,image.getWidth(),image.getHeight());
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
                    hud.HEALTH -= 10f;
                    Game.Destroy(tempObject);
                }
            }
        }
    }

    public void render(Graphics g)
    {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);

        if(state == ZombieState.Hunting)
        {
            at.rotate(rotation, image.getWidth() / 2, image.getHeight() / 2);
        }

        if(state == ZombieState.Standing)
        {
            at.rotate(lastRotation, image.getWidth() / 2, image.getHeight() / 2);
        }

        Graphics2D g2d = (Graphics2D) g;

        // Draw the Player's Sprite
        g2d.drawImage(image, at, null);
        g2d.drawString(state.toString(), x, y + 15);
    }
}
