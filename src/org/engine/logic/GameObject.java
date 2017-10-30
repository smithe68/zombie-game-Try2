package org.engine.logic;

import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject implements Comparable<GameObject>
{
    public String name;
    public String tag;

    public double x, y;
    public double posX, posY;
    public double velX, velY;
    public double rotation;

    public int width, height;
    public int layer;

    protected boolean isDynamic;
    protected boolean isPersistant;
    protected boolean isTrigger;

    protected BufferedImage image;

    public GameObject(double x, double y)
    {
        this.x = x;
        this.y = y;

        width = 16;
        height = 16;

        name = getClass().getSimpleName();
        tag = name;
    }

    /* Updates the GameObject */
    public void update() { }

    /* Renders the GameObject */
    public void render(Graphics2D g) { }

    /* Draws GameObject relative to Camera */
    public void renderTransform()
    {
        posX = (x - width / 2) - Camera.x + Renderer.getResolution().width / 2;
        posY = (y - height / 2) - Camera.y + Renderer.getResolution().height / 2;
    }

    /* Do Collision Detection Here */
    public void collision()
    {
        if(!isDynamic | !isTrigger)
            return;

        for(int i = 0; i < Level.objects.size(); i++)
        {
            if(Level.objects.get(i) == this) continue;

            GameObject curr = Level.objects.get(i);

            if(getBounds().intersects(Level.objects.get(i).getBounds()))
            {
                onCollision(curr);

                if(!isTrigger)
                {
                    // Colliding Right
                    if(x <= curr.x)
                        if(velX > 0) { velX = 0; }

                    // Colliding Left
                    if(x >= curr.x)
                        if(velX < 0) { velX = 0; }

                    // Colliding Up
                    if(y >= curr.y)
                        if(velY < 0) { velY = 0; }

                    // Colliding Down
                    if(y <= curr.y)
                        if(velY > 0) { velY = 0; }
                }
            }
        }
    }

    /* Returns what the Object is Colliding with */
    public void onCollision(GameObject g) { }

    /* Returns the Collision Bounds */
    public Rectangle getBounds() {
        return new Rectangle((int)posX, (int)posY, width, height);
    }

    /* Do Physics Calculations */
    public void physics()
    {
        if(!isDynamic)
            return;

        velX *= 1.0 - ((1.0 - 0.9) * 0.025);
        velY *= 1.0 - ((1.0 - 0.9) * 0.025);

        x += velX;
        y += velY;
    }

    @Override
    public int compareTo(GameObject o) {
        return layer - o.layer;
    }
}
