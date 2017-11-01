package org.objects;

import org.engine.logic.GameObject;
import org.engine.logic.Input;
import org.engine.logic.Level;
import org.engine.rendering.Camera;
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

        double xDiff = (Input.mousePos.x / Renderer.getResolutionFactor().width) - Renderer.getResolution().width / 2;
        double yDiff = (Input.mousePos.y / Renderer.getResolutionFactor().height) - Renderer.getResolution().height / 2;

        dirX = xDiff - x + Camera.x;
        dirY = yDiff - y + Camera.y;
    }

    @Override
    public void update()
    {
        velX = dirX * 0.025;
        velY = dirY * 0.025;
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
