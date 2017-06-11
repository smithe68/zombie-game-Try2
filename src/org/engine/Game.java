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

        Game.Instantiate(new HUD(0, 0, ID.HUD));

        Game.Instantiate(new Player(100, 100, ID.Player));

        Game.Instantiate(new BasicZombie(300, 150, ID.BasicZombie));
        Game.Instantiate(new BasicZombie(350, 200, ID.BasicZombie));
        Game.Instantiate(new BasicZombie(200, 300, ID.BasicZombie));
    }

    public static void Instantiate(GameObject object)
    {
        World.currentWorld.gameObjects.add(object);
    }

    public static void quit()
    {
        System.exit(1);
    }

}
