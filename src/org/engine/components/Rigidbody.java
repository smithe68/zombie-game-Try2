package org.engine.components;

import org.engine.logic.GameObject;

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

    public void setDebugView(boolean debug) {
        this.debug = debug;
    }
}
