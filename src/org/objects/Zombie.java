package org.objects;

import org.engine.components.*;
import org.engine.logic.*;
import org.engine.portation.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Zombie extends GameObject
{
    private Rigidbody rb;
    private BoxCollider coll;

    private Player player;

    public Zombie(double x, double y)
    {
        super(x, y);

        image = SpriteLoader.getSprite("BasicZombie.png");

        player = (Player)Level.findObject("Player");

        rb = addComponent(new Rigidbody(this));
        coll = addComponent(new BoxCollider(this));
        coll.setTrigger(true);
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
        if(Mathf.distance(x, y, player.x, player.y) < 100)
            rb.moveTo(player, 0.25);
    }

    @Override
    public void render(Graphics2D g)
    {
        rotation = Mathf.lookAt(this, player);
        AffineTransform transform = g.getTransform();
        g.rotate(rotation, posX + width / 2, posY + height / 2);
        g.drawImage(image, (int)posX, (int)posY, width, height, null);
        g.setTransform(transform);
    }
}
