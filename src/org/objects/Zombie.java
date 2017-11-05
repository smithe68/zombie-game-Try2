package org.objects;

import org.engine.components.*;
import org.engine.logic.*;
import org.engine.math.Mathf;
import org.engine.portation.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Zombie extends GameObject
{
    public double health = 100;

    private Rigidbody rb;
    private BoxCollider coll;
    private ProgressBar healthBar;

    private Player player;

    public Zombie(double x, double y)
    {
        super(x, y);

        image = SpriteLoader.getSprite("BasicZombie.png");

        player = (Player)Level.findObject("Player");

        rb = addComponent(new Rigidbody(this));
        coll = addComponent(new BoxCollider(this));
        coll.setTrigger(true);

        // Create HUD
        healthBar = (ProgressBar)Level.instantiate(new ProgressBar(5, 5));
        healthBar.setColors(Color.white, Color.red, Color.white);
        healthBar.setValues(1, (int)health, 100);
        healthBar.setAttributes(15, 2);
        healthBar.setShowAmount(false);
    }

    @Override
    public void onCollision(GameObject g)
    {
        if(g.tag.equals("Player"))
            player.hurt(0.5);

        if(g.tag.equals("Bullet"))
        {
            health -= 20;
            Level.destroy(g);
        }
    }

    @Override
    public void update()
    {
        if(Mathf.distance(x, y, player.x, player.y) < 100)
            rb.moveTo(player, 5);

        healthBar.x = posX;
        healthBar.y = posY - 2.5;

        healthBar.setAmount((int)health);
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
