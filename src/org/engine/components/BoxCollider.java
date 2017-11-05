package org.engine.components;

import org.engine.logic.GameObject;
import org.engine.logic.Level;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoxCollider extends Component
{
    private boolean isTrigger;
    private GameObject parent;

    public BoxCollider(GameObject g) {
        this.parent = g;
    }

    @Override
    public void update()
    {
        for(int i = 0; i < Level.objects.size(); i++)
        {
            GameObject curr = Level.objects.get(i);
            if(curr == parent | !curr.containsBounds) continue;

            if(parent.getBounds().intersects(Level.objects.get(i).getBounds()))
            {
                parent.onCollision(curr);

                if(!isTrigger)
                {
                    // Colliding Right
                    if(parent.x <= curr.x)
                        if(parent.velX > 0) { parent.velX = 0; }

                    // Colliding Left
                    if(parent.x >= curr.x)
                        if(parent.velX < 0) { parent.velX = 0; }

                    // Colliding Up
                    if(parent.y >= curr.y)
                        if(parent.velY < 0) { parent.velY = 0; }

                    // Colliding Down
                    if(parent.y <= curr.y)
                        if(parent.velY > 0) { parent.velY = 0; }
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        if(debug)
        {
            g.setColor(Color.red);
            g.draw(new Rectangle2D.Double(parent.posX, parent.posY, parent.width, parent.height));
        }
    }

    /* Set if this Collider is a Trigger */
    public void setTrigger(boolean isTrigger) {
        this.isTrigger = isTrigger;
    }

    /* Set if Debug Rendering is Active */
    public void enableDebug() {
        this.debug = true;
    }
}
