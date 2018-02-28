package org.engine.logic;

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
public class Entity implements Comparable<Entity>
{
    public String name;
    public String tag;

    public double x, y;
    public int posX, posY;

    public int height = 16;
    public int width = 16;

    public int layer = 0;

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

        components = new LinkedList<>();
    }

    public Entity() { this(0, 0); }

    /* Updates the Entities Components */
    final void update()
    {
        for(Component comp : components) {
            comp.update();
        }
    }

    /* Used for rendering this entity */
    final void render(Graphics2D g)
    {
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

    /* Adds a component to this entity */
    public final Component addComponent(Component comp)
    {
        for (Component component : components)
        {
            if (component.getClass().isInstance(comp)) {
                return component;
            }
        }

        components.add(comp);

        return comp;
    }

    /* Returns all the components attached */
    public final LinkedList<Component> getComponents() {
        return components;
    }

    @Override
    public int compareTo(Entity e) {
        return layer - e.layer;
    }
}
