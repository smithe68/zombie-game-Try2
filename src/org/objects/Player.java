package org.objects;

import org.engine.input.Input;
import org.engine.logic.Entity;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Entity
{
    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void update()
    {
        x += Input.getInputAxis("Horizontal") * 2;
        y += Input.getInputAxis("Vertical") * 2;
    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
        g.setColor(Color.red);

        rotation += 1;

        if(rotation > 360)
            rotation = 0;

        g.rotate(Math.toRadians(rotation), posX + getWidth() / 2, posY + getHeight() / 2);
        g.fillRect(posX, posY, getWidth(), getHeight());
        g.setTransform(at);
    }
}
