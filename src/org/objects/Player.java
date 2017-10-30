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

    private Dimension res;
    private Dimension window;

    private ProgressBar healthBar;

    public Player(double x, double y)
    {
        super(x, y);
        image = SpriteLoader.getSprite("PlayerDude.png");
        isDynamic = true;

        // Game Resolution and Window Size
        res = Renderer.getResolution();
        window = new Dimension(Window.getWidth(), Window.getHeight());

        // Create HUD
        healthBar = (ProgressBar)Level.instantiate(new ProgressBar(5, 5));
        healthBar.setColors(Color.white, Color.red, Color.white);
        healthBar.setValues(1, (int)health, 100);
        healthBar.setAttributes(30, 5);
        healthBar.setShowAmount(false);
    }

    @Override
    public void update()
    {
        Camera.x = x;
        Camera.y = y;

        velX = Input.horizontal * 2;
        velY = Input.vertical * 2;

        healthBar.setAmount((int)health);
    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform transform = g.getTransform();

        double factorX = window.width / Renderer.getResolution().width;
        double factorY = window.height / Renderer.getResolution().height;

        double xDiff = (Input.mousePos.x / factorX) - res.width / 2;
        double yDiff = (Input.mousePos.y / factorY) - res.height / 2;

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
    }

    /* Heal the Player's Health */
    public void heal(double amount)
    {
        if(health < 100)
            health += amount;
    }
}
