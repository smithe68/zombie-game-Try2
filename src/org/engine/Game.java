package org.engine;

import org.enums.ID;
import org.enums.PickupTypes;
import org.objects.*;
import org.world.World;

import static org.engine.sounds.music;

public class Game
{
    public static void main(String[] args)
    {
        Renderer.init();

        World.currentWorld = new World();

        LevelGeneration gen = new LevelGeneration();
        gen.CreateLevel();

        Game.Instantiate(new Player(220, 100, ID.Player));

        //Game.Instantiate(new BasicZombie(300, 150, ID.BasicZombie));
        //Game.Instantiate(new BasicZombie(350, 200, ID.BasicZombie));
        //Game.Instantiate(new BasicZombie(200, 300, ID.BasicZombie));

        Game.Instantiate(new Pickup(300, 100, ID.Pickup, PickupTypes.Pistol));
        music();
    }

    public static GameObject Instantiate(GameObject object)
    {
        World.currentWorld.gameObjects.add(object);

        return object;
    }

    public static void quit()
    {
        System.exit(1);
    }

}
