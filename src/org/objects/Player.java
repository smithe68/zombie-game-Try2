package org.objects;

import org.engine.components.ShapeRenderer;
import org.engine.input.Input;
import org.engine.logic.Entity;

import java.awt.*;

public class Player extends Entity
{
    @Override
    public void start()
    {
        color = Color.white;
        ShapeRenderer r = (ShapeRenderer)addComponent(new ShapeRenderer(this));
        r.setDebugMode(true);
    }

    @Override
    public void update()
    {
        x += Input.getInputAxis("Horizontal") * 2;
        y += Input.getInputAxis("Vertical") * 2;
    }
}
