package org.objects;

import org.engine.logic.UIObject;

import java.awt.*;

public class ProgressBar extends UIObject
{
    public double min = 1;
    public double amount;
    public double max;

    public int borderSize = 1;
    public int textSize = 12;

    public Color border;
    public Color content;
    public Color text;

    public boolean showAmount;

    public ProgressBar(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g)
    {
        // Draw Border
        g.setColor(border);
        g.fillRect((int)x, (int)y, width, height);

        // Draw Content
        g.setColor(content);
        g.fillRect((int)x, (int)y, (int)(width * (amount / max)), height);

        // Draw Amount String
        if(showAmount)
        {
            g.setColor(text);
            Font font = new Font("Arial", Font.PLAIN, textSize);
            FontMetrics metrics = g.getFontMetrics(font);

            int fontX = (int)(x + (width - metrics.stringWidth(String.valueOf(amount))) / 2);
            int fontY = (int)(y + (height - metrics.getHeight()) / 2) + metrics.getAscent();

            g.setFont(font);
            g.drawString(String.valueOf(amount), fontX, fontY);
        }
    }

    /* Sets the Main Content Values */
    public void setValues(int min, int amount, int max)
    {
        this.min = min;
        this.amount = amount;
        this.max = max;
    }

    /* Sets the Main Default Attributes */
    public void setAttributes(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /* Set the Colors of the Progress Bar */
    public void setColors(Color border, Color content, Color text)
    {
        this.border = border;
        this.content = content;
        this.text = text;
    }

    /* Sets the Border Size */
    public void setBorderSize(int size) { borderSize = size; }

    /* Set the Amount relative to min and max */
    public void setAmount(int amount) { this.amount = amount; }

    /* Set to Show the Amount */
    public void setShowAmount(boolean show) { showAmount = show; }

    /* Set Text Size */
    public void setTextSize(int size) { textSize = size; }
}
