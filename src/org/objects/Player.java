package org.objects;

import org.engine.logic.Entity;

import java.awt.*;

public class Player extends Entity
{
    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.red);
        g.fillRect(posX, posY, width, height);
    }
}
