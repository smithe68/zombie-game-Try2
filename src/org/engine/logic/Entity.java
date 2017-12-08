package org.engine.logic;

import org.engine.components.ShapeRenderer;
import org.engine.rendering.Renderer;

import java.awt.*;
import java.util.LinkedList;

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

    public double x, y;
    public int posX, posY;

    public int height = 16;
    public int width = 16;

    public Color color;

    private Dimension res;

    private LinkedList<Component> components;

    public Entity(final double x, final double y)
    {
        this.x = x;
        this.y = y;

        name = getClass().getSimpleName();
        tag = name;

        // Retrieve Rendering Resolution
        res = Renderer.getResolution();

        start();
    }

    public Entity() { this(0, 0); }

    /* Used when the object is spawned */
    public abstract void start();

    /* Used for doing game logic */
    public abstract void update();

    /* Updates the Entities Components */
    final void updateComponent()
    {
        for(Component comp : components) {
            comp.update();
        }
    }

    /* Used for rendering this entity */
    final void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(posX, posY, width, height);

        for(Component comp : components) {
            comp.render(g);
        }
    }

    /* Calculates the rendering position */
    final void renderTransform()
    {
        posX = ((int)x - width / 2) + res.width / 2;
        posY = ((int)(y * -1) - height / 2) + res.height / 2;
    }

    protected final Component addComponent(Component comp)
    {
        components.add(comp);
        return comp;
    }
}
