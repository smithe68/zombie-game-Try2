package org.ui;

import org.engine.CustomMath;
import org.engine.GameObject;
import org.enums.ID;

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
        HEALTH = CustomMath.Clamp(HEALTH,0,100);

        // Change Color of Health
        greenValue = (int) CustomMath.Clamp(greenValue, 0, 255);
        greenValue = (int)HEALTH * 2;
    }

    public void Render(Graphics g)
    {
        // Draw Health Bar
        g.setColor(Color.gray);
        g.fillRect((int) posX,(int) posY,sizeX,sizeY);
        g.setColor(new Color(150, greenValue, 0));
        g.fillRect((int) posX,(int) posY,(int)(HEALTH/healthBarX) ,sizeY);
        g.setColor(Color.white);
        g.drawRect((int) posX,(int) posY,sizeX,sizeY);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
