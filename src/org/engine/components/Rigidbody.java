package org.engine.components;

import org.engine.logic.GameObject;
import org.engine.logic.Updater;
import org.engine.math.Vector;

import java.awt.*;
import java.awt.geom.Line2D;

public class Rigidbody extends Component
{
    private GameObject parent;

    public Rigidbody(GameObject parent) {
        this.parent = parent;
    }

    @Override
    public void update()
    {
        parent.velX *= 1.0 - ((1.0 - 0.9) * Updater.DELTA_TIME);
        parent.velY *= 1.0 - ((1.0 - 0.9) * Updater.DELTA_TIME);

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
        Vector dir = new Vector(target.x - parent.x, target.y - parent.y);
        dir.normalize();

        parent.velX = dir.x * (speed * 0.1);
        parent.velY = dir.y * (speed * 0.1);
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
