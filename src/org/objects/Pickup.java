package org.objects;

import org.engine.CustomMathf;
import org.engine.Renderer;
import org.enums.ID;
import org.enums.PickupTypes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pickup extends GameObject
{
    private BufferedImage currentImage;

    private BufferedImage pistol;

    public PickupTypes type;

    private float rotation = 0;
    private float scale = 1;
    private float setScale = 1;

    public Pickup(int x, int y, ID id, PickupTypes type)
    {
        super(x, y, id);

        this.type = type;

        try
        {
            pistol = Renderer.LoadImage("/resources/sprites/Pistol_Side.png");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        ChooseType();
    }

    public void tick(float deltaTime)
    {
        rotation += 2 * deltaTime;

        if(scale >= 1.4f) setScale = 1f;
        if(scale <= 1.1) setScale = 1.5f;
        System.out.println(scale);
        scale = CustomMathf.Lerp(scale, setScale, deltaTime);
    }

    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale * 1.1, scale * 1.1);

        at.rotate(rotation, currentImage.getWidth() / 2,
                currentImage.getHeight() / 2);


        // Draw the Player's Sprite
        g2d.drawImage(currentImage, at, null);
    }

    public Rectangle getBounds()
    {
        return null;
    }

    public void ChooseType()
    {
        switch (type)
        {
            case Pistol:
                currentImage = pistol;
                break;
        }
    }
}

