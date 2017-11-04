package org.engine.components;

import java.awt.*;

public abstract class Component
{
    protected boolean debug;

    public abstract void update();
    public abstract void render(Graphics2D g);
}
