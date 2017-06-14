package org.world;

import org.engine.Renderer;
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

        Renderer.camPosX = (Renderer.camX + Renderer.gameWidth / 2) / 4.8f;
        Renderer.camPosY = (Renderer.camY + Renderer.gameHeight / 2) / 4.32f;

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
