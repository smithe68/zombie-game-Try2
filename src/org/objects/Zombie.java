package org.objects;

import org.engine.logic.*;
import org.engine.portation.*;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class Zombie extends GameObject
{
    private GameObject player;

    public Zombie(float x, float y)
    {
        super(x, y);

        image = SpriteLoader.getSprite("BasicZombie.png");
        player = (GameObject)Level.findObject("Player");
    }

    @Override
    public void update()
    {
        float dist = (float)Math.sqrt(Math.pow((x - player.x), 2) + (Math.pow(y - player.y, 2)));

        if(dist < 100)
        {
            x = CustomMath.moveToward(x, player.x, 0.25f);
            y = CustomMath.moveToward(y, player.y, 0.25f);
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        // Set the Transform for the Player
        AffineTransform transform = g.getTransform();

        rotation = (float)Math.toDegrees(Math.atan2(player.posY - posY, player.posX - posX));

        // Rotate the Player to Mouse Cursor
        g.rotate(Math.toRadians(rotation), posX + width / 2, posY + height / 2);

        // Draw the Player Sprite
        g.drawImage(image, (int)posX, (int)posY, width, height, null);

        // Set Position of Player
        g.setTransform(transform);
    }
}
