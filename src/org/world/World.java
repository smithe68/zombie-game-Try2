package org.world;

import org.objects.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class World
{
    public static World currentWorld = null;

    private static long lastTime = System.nanoTime();

    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    public static void update()
    {
        float deltaTime = (System.nanoTime() - lastTime) / 1000000000.0f;
        lastTime = System.nanoTime();

        for(GameObject gameObject : currentWorld.gameObjects)
        {
            gameObject.tick(deltaTime);
        }
    }

    public static void render(Graphics g)
    {
        for(GameObject gameObject : currentWorld.gameObjects)
        {
            gameObject.render(g);
        }
    }
}
