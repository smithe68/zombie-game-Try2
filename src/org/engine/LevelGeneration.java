package org.engine;

import org.objects.Tile;

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

    public int levelWidth = 20, levelHeight = 20;

    private BufferedImage stoneTile;

    public void CreateLevel()
    {
        for(int x = 0; x < levelWidth; x++)
        {
            for(int y = 0; y < levelHeight; y++)
            {
                Game.Instantiate(new Tile(x * stoneTile.getWidth(), y * stoneTile.getHeight(), ID.Tile, stoneTile));
            }
        }
    }
}
