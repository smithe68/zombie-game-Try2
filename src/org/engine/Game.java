package org.engine;

import org.enums.ID;
import org.enums.PickupTypes;
import org.objects.*;

public class Game
{
    // Used For Starting the Game
    public static void main(String[] args)
    {
        // Start Rendering
        Renderer.init();

        // Create the Level
        Level.currLevel = new Level();

        // Generate Background
        LevelGeneration gen = new LevelGeneration();
        gen.CreateLevel();

        Game.Instantiate(new Player(100, 100, ID.Player));
        Game.Instantiate(new BasicZombie(300, 150, ID.BasicZombie));
        Game.Instantiate(new Pickup(100, 200, ID.Pickup, PickupTypes.Pistol, 1, true));

        // Start Background Music
        Sound.PlaySound("/resources/sounds/bg.wav", -20.0f,true);
    }

    // Creates a GameObject in the Level
    public static GameObject Instantiate(GameObject object)
    {
        Level.currLevel.objects.add(object);
        return object;
    }

    // Destroy a GameObject in the Level
    public static void Destroy(GameObject object)
    {
        int objIndex = Level.currLevel.objects.indexOf(object);
        Level.objects.remove(objIndex);
    }

    // Quits the Game
    public static void quit()
    {
        System.out.println("Quitting!");
        System.exit(1);
    }

}
