package org.objects;

import org.engine.components.BoxCollider;
import org.engine.logic.*;
import org.engine.portation.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Zombie extends GameObject
{
    private Player player;

    public Zombie(double x, double y)
    {
        super(x, y);

        image = SpriteLoader.getSprite("BasicZombie.png");
        player = (Player)Level.findObject("Player");
        isTrigger = true;

        components.add(new BoxCollider(this));
    }

    @Override
    public void onCollision(GameObject g)
    {
        if(g.tag.equals("Player"))
            player.hurt(0.5);
    }

    @Override
    public void update()
    {
        // Get Distance between Player and Zombie
        double dist = Math.sqrt(((x - player.x) * (x - player.x)) + ((y - player.y) * (y - player.y)));

        double dirX = player.x - x;
        double dirY = player.y - y;

        if(dist < 100)
        {
            velX = dirX * 0.025;
            velY = dirY * 0.025;
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
