package org.objects;

import org.engine.GameObject;
import org.enums.ID;
import org.ui.WeaponInfo;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends GameObject
{
    public WeaponInfo info;

    private double xDiff;
    private double yDiff;

    private double angle;

    public Bullet(int x, int y, ID id, WeaponInfo info, double xDiff, double yDiff, double angle)
    {
        super(x, y, id);
        this.info = info;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.angle = angle;
    }

    public void Update()
    {
        posX += (xDiff) / 20;
        posY += (yDiff) / 20;
    }

    public void Render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform transform = g2d.getTransform();

        g2d.setColor(Color.yellow);

        // Bottom Left
        if (angle > 90 && angle < 180) {g2d.fillRect((int) (posX +15), (int)(posY +36), 2, 2);}

        // Bottom Right
        if (angle > 0 && angle < 90) {g2d.fillRect((int) (posX +15), (int)(posY +15), 2, 2);}

        // Top Left
        if (angle < -90) {g2d.fillRect((int) (posX -6), (int)(posY +36), 2, 2);}

        // Top Right
        if (angle <= 0 && angle > -90 ) {g2d.fillRect((int) (posX -2), (int)(posY +15), 2, 2);}

        g2d.setTransform(transform);
    }

    public Rectangle getBounds()
    {
        int centerX = (int)(posX - image.getWidth() / 2);
        int centerY = (int)(posY - image.getHeight() / 2);

        return new Rectangle(centerX, centerY,2,2);
    }
}
