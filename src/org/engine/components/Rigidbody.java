package org.engine.components;

import org.engine.logic.GameObject;
import org.engine.logic.Updater;

import java.awt.*;
import java.awt.geom.Line2D;

public class Rigidbody implements Component
{
    private boolean debug;
    private GameObject parent;

    public Rigidbody(GameObject parent) {
        this.parent = parent;
    }

    @Override
    public void update()
    {
        parent.velX *= 1.0 - ((1.0 - 0.9) * 0.025);
        parent.velY *= 1.0 - ((1.0 - 0.9) * 0.025);

        parent.x += parent.velX;
        parent.y += parent.velY;
    }

    @Override
    public void render(Graphics2D g)
    {
        if(debug)
        {
            g.setColor(Color.white);

            double startX = parent.posX + parent.width / 2;
            double startY = parent.posY + parent.height / 2;

            double dirX = startX + (parent.velX * 15);
            double dirY = startY + (parent.velY * 15);

            g.draw(new Line2D.Double(startX, startY, dirX, dirY));
        }
    }

    /* Move the Parent GameObjec toward the Target */
    public void moveTo(GameObject target, double speed)
    {
        double dirX = target.x - parent.x;
        double dirY = target.y - parent.y;

        parent.velX = dirX * (speed * Updater.DELTA_TIME);
        parent.velY = dirY * (speed * Updater.DELTA_TIME);
    }

    /* Adds to current Velocity of the Parent GameObject */
    public void addVelocity(double dx, double dy)
    {
        parent.velX += dx;
        parent.velY += dy;
    }

    /* Sets the Velocity of the Parent GameObject */
    public void setVelocity(double x, double y)
    {
        parent.velX = x;
        parent.velY = y;
    }

    /* Set if Debug Rendering is Active */
    public void enableDebug() {
        this.debug = true;
    }
}
