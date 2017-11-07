package org.engine.logic;

import org.engine.rendering.Renderer;

import java.awt.*;

/**
 * This class is a basic object that resides
 * in the game. Everything that is rendered and
 * contains game logic should be derived from
 * this.
 *
 * @author Jakub P. Szarkowicz
 */
public abstract class Entity
{
    public String name;
    public String tag;

    protected double x, y;
    protected int posX, posY;

    protected int height = 16;
    protected int width = 16;

    private Dimension res;

    public Entity(final double x, final double y)
    {
        this.x = x;
        this.y = y;

        name = getClass().getSimpleName();
        tag = name;

        // Retrieve Rendering Resolution
        res = Renderer.getResolution();
    }

    /** Used for doing game logic */
    public abstract void update();

    /** Used for rendering this entity */
    public abstract void render(Graphics2D g);

    public void renderTransform()
    {
        posX = ((int)x - width / 2) + res.width / 2;
        posY = ((int)(y * -1) - height / 2) + res.height / 2;
    }
}
