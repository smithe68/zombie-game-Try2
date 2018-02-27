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
}
