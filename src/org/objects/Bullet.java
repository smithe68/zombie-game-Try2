package org.objects;

import org.enums.ID;
import org.ui.WeaponInfo;
import org.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static org.objects.Player.player;

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

    public void tick(float deltaTime)
    {
        x += (xDiff) / 20;
        y += (yDiff) / 20;
    }

    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform transform = g2d.getTransform();

        g2d.setColor(Color.yellow);

        // Bottom Left
        if (angle > 90 && angle < 180) {g2d.fillRect((int) (x+15), (int)(y+36), 2, 2);}

        // Bottom Right
        if (angle > 0 && angle < 90) {g2d.fillRect((int) (x+15), (int)(y+15), 2, 2);}

        // Top Left
        if (angle < -90) {g2d.fillRect((int) (x-6), (int)(y+36), 2, 2);}

        // Top Right
        if (angle <= 0 && angle > -90 ) {g2d.fillRect((int) (x-2), (int)(y+15), 2, 2);}

        g2d.setTransform(transform);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,2,2);
    }
}
