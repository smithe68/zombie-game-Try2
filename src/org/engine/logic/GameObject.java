package org.engine.logic;

import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;
import org.engine.components.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    protected boolean isPersistant;
    protected BufferedImage image;

    // Contains all the Components in a GameObject
    protected ArrayList<Component> components = new ArrayList<>();

    public GameObject(double x, double y)
    {
        this.x = x;
        this.y = y;

        width = 16;
        height = 16;

        name = getClass().getSimpleName();
        tag = name;
    }

    public void updateComponents()
    {
        for(int i = 0; i < components.size(); i++)
            components.get(i).update();
    }

    public void renderComponents(Graphics2D g)
    {
        for(int i = 0; i < components.size(); i++)
            components.get(i).render(g);
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

    /* Returns what the Object is Colliding with */
    public void onCollision(GameObject g) { }

    /* Returns the Collision Bounds */
    public Rectangle getBounds() {
        return new Rectangle((int)posX, (int)posY, width, height);
    }

    /* Do Physics Calculations */
    public void physics()
    {
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
