package org.engine.components;

import org.engine.logic.Component;
import org.engine.logic.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteRenderer extends Component
{
    public Color tint;
    public BufferedImage sprite;

    public int width;
    public int height;

    private double currentFrame;
    private int currentAnimIndex;

    private static GraphicsConfiguration gfx;

    public SpriteRenderer(Entity parent)
    {
        super(parent);
        height = parent.height;
        width = parent.width;
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(tint);
        g.drawImage(sprite, parent.posX, parent.posY, width, height, null);
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public static BufferedImage getImage(String name)
    {
        String path = "/resources/sprites/" + name;
        BufferedImage finalImage = null;
        BufferedImage raw;

        try
        {
            raw = ImageIO.read(SpriteRenderer.class.getResource(path));
            finalImage = gfx.createCompatibleImage(raw.getWidth(), raw.getHeight(), raw.getTransparency());
            finalImage.getGraphics().drawImage(raw, 0, 0, raw.getWidth(), raw.getHeight(), null);
        }
        catch(IOException e) {
            System.err.println("Image with name \"" + name + "\" not found!");
        }

        return finalImage;
    }

    public static BufferedImage[] splitSheet(BufferedImage image, int x, int y, int cellWidth, int cellHeight, int cells)
    {
        BufferedImage[] result = new BufferedImage[cells];

        for(int i = 0; i < cells; i++) {
           result[i] = image.getSubimage(x + i * cellWidth, y, cellWidth, cellHeight);
        }

        return result;
    }

    public BufferedImage animateSheet(BufferedImage[] sheet, double time)
    {
        currentFrame += 0.5f;

        if(currentFrame >= time)
        {
            currentAnimIndex += 1;
            if(currentAnimIndex > sheet.length - 1) { currentAnimIndex = 0; }
            currentFrame = 0;
        }

        return sheet[currentAnimIndex];
    }

    public static void setGraphicsConfiguration(Canvas canvas) {
        gfx = canvas.getGraphicsConfiguration();
    }
}
