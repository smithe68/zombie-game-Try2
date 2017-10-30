package org.objects;

import org.engine.logic.*;
import org.engine.portation.*;
import org.engine.rendering.Window;
import org.engine.rendering.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject
{
    public double health = 100;

    public Player(double x, double y)
    {
        super(x, y);
        image = SpriteLoader.getSprite("PlayerDude.png");
        isDynamic = true;
    }

    @Override
    public void update()
    {
        Camera.x = x;
        Camera.y = y;

        velX = Input.horizontal * 2;
        velY = Input.vertical * 2;
    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform transform = g.getTransform();

        double factorX = Window.getWidth() / Renderer.getResolution().width;
        double factorY = Window.getHeight() / Renderer.getResolution().height;

        double xDiff = (Input.mousePos.x / factorX) - Renderer.getResolution().width / 2;
        double yDiff = (Input.mousePos.y / factorY) - Renderer.getResolution().height / 2;

        rotation = Math.toDegrees(Math.atan2(yDiff - y + Camera.y, xDiff - x + Camera.x));
        g.rotate(Math.toRadians(rotation), posX + width / 2, posY + height / 2);
        g.drawImage(image, (int)posX, (int)posY, width, height, null);
        g.setTransform(transform);
    }

    /* Hurt the Player's Health */
    public void hurt(double amount)
    {
        if(health > 0)
            health -= amount;
        System.out.println("Health: " + health);
    }
}
