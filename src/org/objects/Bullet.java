package org.objects;

import org.enums.ID;
import org.ui.WeaponInfo;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends GameObject
{
    public WeaponInfo info;

    private double xDiff;
    private double yDiff;

    public Bullet(int x, int y, ID id, WeaponInfo info, double xDiff, double yDiff)
    {
        super(x, y, id);
        this.info = info;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
    }

    public void tick(float deltaTime)
    {
        x += (xDiff) / 30;
        y += (yDiff) / 30;
    }

    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform transform = g2d.getTransform();

        // Draw the Player's Sprite
        //g2d.rotate(angle, (int)x, (int)y);
        g2d.fillRect((int) (x-6), (int)(y+36), 2, 2);
        g2d.setTransform(transform);
    }

    public Rectangle getBounds()
    {
        return null;
    }
}
