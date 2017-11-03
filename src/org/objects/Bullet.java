package org.objects;

import org.engine.logic.*;
import org.engine.rendering.Renderer;

import java.awt.*;

public class Bullet extends GameObject
{
    private double dirX;
    private double dirY;

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

        dirX = xDiff - Renderer.getResolution().width / 2;
        dirY = yDiff - Renderer.getResolution().height / 2;
    }

    @Override
    public void update()
    {
        velX = dirX * 0.1;
        velY = dirY * 0.1;
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
        if(g.tag.equals("Zombie") | g.tag.equals("Bullet"))
        {
            Level.destroy(g);
        }
    }
}
