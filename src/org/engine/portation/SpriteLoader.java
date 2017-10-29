package org.engine.portation;

import org.engine.rendering.Renderer;
import org.engine.rendering.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader
{
    /* Loads an Image from Project Files */
    public static BufferedImage loadImage(String path) throws IOException
    {
        BufferedImage raw = ImageIO.read(Renderer.class.getResource(path));
        GraphicsConfiguration gc = Window.getCanvas().getGraphicsConfiguration();
        BufferedImage finalImage = gc.createCompatibleImage(raw.getWidth(), raw.getHeight(), raw.getTransparency());
        finalImage.getGraphics().drawImage(raw, 0, 0, raw.getWidth(), raw.getHeight(), null);
        return finalImage;
    }

    /* Returns an Image inside the Project's Sprite Folder */
    public static BufferedImage getSprite(String name)
    {
        try {
            return loadImage("/resources/sprites/" + name);
        }
        catch (IOException e) {
            System.out.println("Sprite [" + name + "] not Found!");
        }

        return null;
    }
}
