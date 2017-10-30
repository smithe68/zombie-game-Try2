package org.objects;

import org.engine.logic.*;
import org.engine.portation.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Zombie extends GameObject
{
    private GameObject player;

    public Zombie(double x, double y)
    {
        super(x, y);

        image = SpriteLoader.getSprite("BasicZombie.png");
        player = (GameObject)Level.findObject("Player");
        isDynamic = true;
    }

    @Override
    public void update()
    {
        // Get Distance between Player and Zombie
        double dist = Math.sqrt(((x - player.x) * (x - player.x)) + ((y - player.y) * (y - player.y)));

        if(dist < 100)
        {

        }
    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform transform = g.getTransform();
        rotation = Math.toDegrees(Math.atan2(player.posY - posY, player.posX - posX));
        g.rotate(Math.toRadians(rotation), posX + width / 2, posY + height / 2);
        g.drawImage(image, (int)posX, (int)posY, width, height, null);
        g.setTransform(transform);
    }
}
