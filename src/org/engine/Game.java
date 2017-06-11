package org.engine;

import org.objects.Follower;
import org.objects.Player;
import org.objects.XSpeedFollower;
import org.objects.YSpeedFollower;
import org.world.World;

public class Game
{
    public static void main(String[] args)
    {
        Renderer.init();

        World.currentWorld = new World();
        System.out.println("Test");
        World.currentWorld.gameObjects.add(new Player(100, 100, ID.Player));
        World.currentWorld.gameObjects.add(new Follower(100, 100, ID.BasicZombie));
        World.currentWorld.gameObjects.add(new XSpeedFollower(100, 100, ID.BasicZombie));
        World.currentWorld.gameObjects.add(new YSpeedFollower(100, 100, ID.BasicZombie));
    }

    public static void quit()
    {
        System.exit(1);
    }
}
