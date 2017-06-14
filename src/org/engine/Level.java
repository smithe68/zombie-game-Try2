package org.engine;

import java.awt.*;
import java.util.ArrayList;

public class Level
{
    public static Level currentWorld = null;

    private static long lastTime = System.nanoTime();

    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    public static void update()
    {
        float deltaTime = (System.nanoTime() - lastTime) / 1000000000.0f;
        lastTime = System.nanoTime();

        Renderer.camPosX = ((Renderer.camX ));
        Renderer.camPosY = ((Renderer.camY ));

        for(int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.get(i).tick(deltaTime);
        }
    }

    public static void render(Graphics g)
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.get(i).render(g);
        }
    }
}
