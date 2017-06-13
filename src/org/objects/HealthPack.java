package org.objects;

import org.engine.CustomMathf;
import org.engine.Renderer;
import org.enums.ID;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by evan on 6/13/2017.
 */
public class HealthPack extends GameObject {


    private BufferedImage image;

    public HealthPack(int x , int y, ID id)
    {
        super(x, y, id);

        try
        {
            // Get the Sprite for the Follower
            image = Renderer.LoadImage("/resources/sprites/HealthPack.png");
        } catch (IOException e) {}
    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y,image.getWidth() / 2,image.getHeight() / 2);
    }

    public void tick(float deltaTime)
    {

    }

    public void render(Graphics g)
    {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
            Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, at, null);
    }
}

