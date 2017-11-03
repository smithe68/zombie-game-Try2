package org.objects;

import org.engine.logic.GameObject;
import org.engine.logic.Input;
import org.engine.logic.Level;
import org.engine.rendering.Renderer;

import java.awt.*;

public class Bullet extends GameObject
{
    private double dirX;
    private double dirY;

    private double timer = 100000;

    public Bullet(double x, double y)
    {
        super(x, y);

        isDynamic = true;
        isTrigger = true;

        width = 2;
        height = 2;
        layer = -1;

        double xDiff = (Input.mousePos.x / Renderer.getResolutionFactor().width);
        double yDiff = (Input.mousePos.y / Renderer.getResolutionFactor().height);

        dirX = (xDiff - Renderer.getResolution().width / 2) / Renderer.getResolution().width;
        dirY = (yDiff - Renderer.getResolution().height / 2) / Renderer.getResolution().height;
    }

    @Override
    public void update()
    {
        velX += dirX;
        velY += dirY;

        if(timer > 0)
            timer -= 0.1f;
        else
            Level.destroy(this);
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.yellow);
        g.fillRect((int)posX, (int)posY, width, height);
    }

    @Override
    public void onCollision(GameObject g)
    {
        if(g.tag.equals("Zombie") | g.tag.equals("Bullet")) {
            Level.destroy(g);
        }
    }
}
