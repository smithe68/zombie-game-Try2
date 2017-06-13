package org.ui;

import org.engine.CustomMathf;
import org.objects.GameObject;
import org.enums.ID;
import org.objects.Player;

import java.awt.*;

public class HUD extends GameObject
{
    private int sizeX;
    private int sizeY;
    private int healthBarX;

    public HUD (float x, float y, ID id, int sizeX, int sizeY)
    {
        super(x, y, id);

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        healthBarX = (int)(HEALTH / sizeX);
    }

    public float HEALTH = 100;

    private int greenValue = 255;

    public void tick(float deltaTime)
    {
        HEALTH = CustomMathf.Clamp(HEALTH,0,100);

        // Change Color of Health
        greenValue = (int) CustomMathf.Clamp(greenValue, 0, 255);
        greenValue = (int)HEALTH * 2;
    }

    public void render(Graphics g)
    {
        // Draw Health Bar
        g.setColor(Color.gray);
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.setColor(new Color(150, greenValue, 0));
        g.fillRect((int)x,(int)y,(int)(HEALTH/healthBarX) ,sizeY);
        g.setColor(Color.white);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
