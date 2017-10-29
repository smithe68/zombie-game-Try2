package org.engine;

import org.objects.*;

import java.awt.*;
import java.util.ArrayList;

public class Level
{
    public static ArrayList<GameObject> objects = new ArrayList<>();

    /* Creates initial objects in the Level */
    public void loadObjects() {
        instantiate(new Player(0, 0));
    }

    /* Updates all Logic for GameObjects in the Level */
    public void update()
    {
        // Updates Every GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).update();
        }
    }

    /* Renders all GameObjects in the Level */
    public void render(Graphics g)
    {
        // Render every GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).renderTransform();
            objects.get(i).render((Graphics2D)g);
        }
    }

    /* Spawns a GameObject into the Level */
    public static Object instantiate(GameObject g)
    {
        System.out.println("[I]: " + g.getClass().getSimpleName());
        objects.add(g);
        return g;
    }

    /* Destroys / Removes an GameObject from the Level */
    public static void destroy(GameObject g)
    {
        System.out.println("[X]: " + g.getClass().getSimpleName());
        objects.remove(g);
    }
}
