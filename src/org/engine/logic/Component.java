package org.engine.logic;

import java.awt.*;

/**
 * A Class that is added onto an Entity to extend its Functionality
 * @author Jakub P. Szarkowicz
 */
public class Component
{
    protected Entity parent;
    protected boolean debugMode;

    public Component(Entity parent) {
        this.parent = parent;
    }

    /* Updates the Component's Logic */
    public void update() {}

    /* Render the Component */
    public void render(Graphics2D g) {}

    /* Sets the Component's Debug Mode */
    public void setDebugMode(boolean active) {
        debugMode = active;
    }
}
