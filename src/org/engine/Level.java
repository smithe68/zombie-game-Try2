package org.engine;

import java.awt.*;
import java.util.ArrayList;

public class Level
{
    // Current Level
    public static Level currLevel = null;

    // Holds all GameObjects in the Level
    public static ArrayList<GameObject> objects = new ArrayList<>();

    public static void Update()
    {
        // Updates Every GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).Update();
        }
    }

    public static void Render(Graphics g)
    {
        // Render every GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).Render(g);
        }
    }
}
