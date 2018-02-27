package org.engine.components;

import org.engine.logic.Component;
import org.engine.logic.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteRenderer extends Component
{
    public Color tint;
    public BufferedImage sprite;

    public int width;
    public int height;

    private double currentFrame;
    private int currentAnimIndex;

    public SpriteRenderer(Entity parent)
    {
        super(parent);

        width = parent.width;
        height = parent.height;
    }

    @Override
    public void update() {

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

    public static BufferedImage getImageFromFile(String name)
    {
        String dir = "C:\\Users\\Jakub\\IdeaProjects\\RPGame\\src\\resources\\sprites";
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(dir + "/" + name));
        }
        catch(IOException e) { e.printStackTrace(); }

        return image;
    }

    public static BufferedImage[] splitSpritesheet(BufferedImage image, int cellWidth, int cellHeight, int cells)
    {
        BufferedImage[] result = new BufferedImage[cells];

        for(int i = 0; i < cells; i++) {
           result[i] = image.getSubimage(i * cellWidth, 0, cellWidth, cellHeight);
        }

        return result;
    }

    public BufferedImage animateSheet(BufferedImage[] sheet, double fps)
    {
        currentFrame += 0.5f;

        if(currentFrame >= fps)
        {
            currentAnimIndex += 1;
            if(currentAnimIndex > sheet.length - 1) { currentAnimIndex = 0; }
            currentFrame = 0;
        }

        return sheet[currentAnimIndex];
    }
}
