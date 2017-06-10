package org.engine;

import org.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Handler
{
    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public GameObject AddObject(GameObject object)
    {
        this.object.add(object);

        return object;
    }

    public void RemoveObject(GameObject object)
    {
        this.object.remove(object);
    }

    public BufferedImage LoadImage(String path) throws IOException
    {
        BufferedImage image;

        image = ImageIO.read(getClass().getResource(path));

        return image;
    }
}
