package org.engine.components;

import org.engine.logic.Component;
import org.engine.logic.Entity;

import java.awt.*;

public class ShapeRenderer extends Component
{
    public ShapeRenderer(Entity parent) {
        super(parent);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(parent.color);
        g.fillRect(parent.posX, parent.posY, parent.width, parent.height);
        g.setColor(Color.red);
        g.drawRect(parent.posX, parent.posY, parent.width, parent.height);
    }
}
