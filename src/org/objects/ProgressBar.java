package org.objects;

import org.engine.logic.UIObject;

import java.awt.*;

public class ProgressBar extends UIObject
{
    public double min;
    public double amount;
    public double max;

    public Color border;
    public Color content;

    public ProgressBar(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g)
    {

    }

    public void setValues(int min, int amount, int max)
    {
        this.min = min;
        this.amount = amount;
        this.max = max;
    }

    public void setAttributes(int width, int height, Color border, Color content)
    {
        this.width = width;
        this.height = height;
        this.border = border;
        this.content = content;
    }
}
