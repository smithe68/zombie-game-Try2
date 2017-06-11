package org.engine;

import org.objects.Tile;
import org.world.World;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelGeneration
{
    public LevelGeneration()
    {
        try
        {
            // Get Sprites for Generation
            stoneTile = Renderer.LoadImage("/resources/sprites/StoneTile.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public int levelWidth = 10, levelHeight = 10;

    private BufferedImage stoneTile;

    public void CreateLevel()
    {
        for(int x = 0; x < levelWidth; x++)
        {
            for(int y = 0; y < levelHeight; y++)
            {

            }
        }
    }
}
