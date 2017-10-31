package org.engine.logic;

import org.objects.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Level
{
    public static ArrayList<GameObject> objects = new ArrayList<>();

    /* Creates initial objects in the Level */
    public void loadObjects()
    {
        instantiate(new Player(0, 0));
        instantiate(new Zombie(40, 40));

        Pickup p = (Pickup)instantiate(new Pickup(-100, -100));
        p.setPickup(1);
    }

    /* Updates all Logic for GameObjects in the Level */
    public void update()
    {
        // Updates Every GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).update();
            objects.get(i).collision();
            objects.get(i).physics();
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
        Collections.sort(objects);
        return g;
    }

    /* Destroys / Removes an GameObject from the Level */
    public static void destroy(GameObject g)
    {
        System.out.println("[X]: " + g.getClass().getSimpleName());
        objects.remove(g);
        Collections.sort(objects);
    }

    /* Gets a Reference to an Object with the same Tag */
    public static Object findObject(String tag)
    {
        for(int i = 0; i < objects.size(); i++)
            if(objects.get(i).tag.equals(tag))
                return objects.get(i);
            else
                return null;

        return null;
    }

    /* Removes all Objects in the Level */
    public void clearLevel() {
        objects.clear();
    }
}
