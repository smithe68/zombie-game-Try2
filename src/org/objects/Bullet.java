package org.objects;

import org.engine.components.BoxCollider;
import org.engine.components.Rigidbody;
import org.engine.logic.GameObject;
import org.engine.logic.Input;
import org.engine.logic.Level;
import org.engine.rendering.Renderer;

import java.awt.*;

public class Bullet extends GameObject
{
    private double dirX;
    private double dirY;

    private double timer = 3;

    public Bullet(double x, double y)
    {
        super(x, y);


        width = 2;
        height = 2;
        layer = -1;

        double xDiff = (Input.mousePos.x / Renderer.getResolutionFactor().width);
        double yDiff = (Input.mousePos.y / Renderer.getResolutionFactor().height);

        dirX = (xDiff - Renderer.getResolution().width / 2) / Renderer.getResolution().width;
        dirY = (yDiff - Renderer.getResolution().height / 2) / Renderer.getResolution().height;

        BoxCollider coll = addComponent(new BoxCollider(this));
        addComponent(new Rigidbody(this));

        coll.setTrigger(true);
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
}
