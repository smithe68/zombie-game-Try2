package org.engine;

import org.objects.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.ui.HUD.HEALTH;


public class LevelGeneration
{
    public LevelGeneration(Handler handler)
    {
        this.handler = handler;

        try
        {
            // Get Sprites for Generation
            stoneTile = handler.LoadImage("/resources/sprites/StoneTile.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Handler handler;

    public int levelWidth = 10, levelHeight = 10;

    private BufferedImage stoneTile;

    public void CreateLevel()
    {
        for(int x = 0; x < levelWidth; x++)
        {
            for(int y = 0; y < levelHeight; y++)
            {
                handler.AddObject(new Tile(x * 96, y * 96, ID.Tile, stoneTile));

            }
        }
    }
}
