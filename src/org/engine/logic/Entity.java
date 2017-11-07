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
    protected double rotation;
    protected int posX, posY;

    private int height = 8;
    private int width = 8;

    private int rWidth;
    private int rHeight;

    private Dimension res;

    public Entity(final double x, final double y)
    {
        this.x = x;
        this.y = y;

        rWidth = width + (Renderer.RESOLUTION / 4);
        rHeight = height + (Renderer.RESOLUTION / 4);

        name = getClass().getSimpleName();
        tag = name;

        // Retrieve Rendering Resolution
        res = Renderer.getResolution();
    }

    /* Used for doing game logic */
    public abstract void update();

    /* Used for rendering this entity */
    public abstract void render(Graphics2D g);

    /* Calculates the rendering position */
    public void renderTransform()
    {
        posX = ((int)x - width / 2) + res.width / 2;
        posY = ((int)(y * -1) - height / 2) + res.height / 2;
    }

    /* Returns the scaled width of the entity */
    protected int getWidth() {
        return rWidth;
    }

    /* Returns the scaled height of the entity */
    protected int getHeight() {
        return rHeight;
    }
}
