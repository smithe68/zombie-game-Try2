package org.engine;

import org.enums.ID;
import org.enums.PickupTypes;
import org.objects.*;
import org.world.World;

public class Game
{
    public static void main(String[] args)
    {
        // Start Rendering Everything
        Renderer.init();

        // Create the Level
        World.currentWorld = new World();

        // Generate Background
        LevelGeneration gen = new LevelGeneration();
        gen.CreateLevel();

        // INSTANTIATE GAMEOBJECTS

        Game.Instantiate(new Player(220, 100, ID.Player));

        Game.Instantiate(new BasicZombie(300, 150, ID.BasicZombie));
        //Game.Instantiate(new BasicZombie(350, 200, ID.BasicZombie));
        //Game.Instantiate(new BasicZombie(200, 300, ID.BasicZombie));

        Game.Instantiate(new Pickup(300, 100, ID.Pickup, PickupTypes.Pistol, 1, true));
        Game.Instantiate(new Pickup(300, 200, ID.Pickup, PickupTypes.Pistol, 1, true));
        Game.Instantiate(new Pickup(200, 200, ID.Pickup, PickupTypes.HealthPack, 1, false));

        // Start Background Music
        Sound.PlaySound("/resources/sounds/bg.wav", -20.0f,true);
    }

    // This Creates a GameObject in the Level
    public static GameObject Instantiate(GameObject object)
    {
        World.currentWorld.gameObjects.add(object);

        return object;
    }

    // This Destroy a GameObject in the Level
    public static void Destroy(GameObject object)
    {
        int objIndex = World.currentWorld.gameObjects.indexOf(object);
        World.gameObjects.remove(objIndex);
    }

    // This Quits the Game
    public static void quit()
    {
        System.exit(1);
    }

}
