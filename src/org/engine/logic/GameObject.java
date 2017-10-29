package org.engine.logic;

import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject
{
    public String name;
    public String tag;

    public float x, y;
    public float posX, posY;
    public int width, height;

    protected boolean hasCollision = true;

    protected BufferedImage image;

    public GameObject(float x, float y)
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

    /* Returns the Collision Bounds */
    public Rectangle getBounds() {
        return new Rectangle((int)posX, (int)posY, width, height);
    }
}
