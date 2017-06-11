package org.engine;

import org.objects.*;
import org.ui.HUD;
import org.world.World;

public class Game
{
    public static void main(String[] args)
    {
        Renderer.init();

        World.currentWorld = new World();

        LevelGeneration gen = new LevelGeneration();
        gen.CreateLevel();

        SpawnGameObjects();
    }

    public static void Instantiate(GameObject object)
    {
        World.currentWorld.gameObjects.add(object);
    }

    public static void quit()
    {
        System.exit(1);
    }

    public static void SpawnGameObjects()
    {
        Instantiate(new HUD(0, 0, ID.HUD));

        Instantiate(new Player(100, 100, ID.Player));
        Instantiate(new BasicZombie(300, 150, ID.BasicZombie));
        Instantiate(new BasicZombie(350, 200, ID.BasicZombie));
        Instantiate(new BasicZombie(200, 300, ID.BasicZombie));
    }
}
