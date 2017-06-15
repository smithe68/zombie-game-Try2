package org.engine;

import org.enums.ID;
import org.objects.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class LevelGeneration
{
    // Generation Settings
    public int levelWidth = 20;
    public int levelHeight = 20;

    // Images for Generation
    private BufferedImage stoneTile;
    private BufferedImage grassTile;

    public LevelGeneration()
    {
        try
        {
            // Get Sprites for Generation
            stoneTile = Renderer.LoadImage("/resources/sprites/StoneTile.png");
            grassTile = Renderer.LoadImage("/resources/sprites/GrassTile.png");
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public void CreateLevel()
    {
        for(int x = -10; x < levelWidth + 10; x++)
        {
            for(int y = -10; y < levelHeight + 10; y++)
            {
                // Make Gen Random
                Random ran = new Random();
                int random = ran.nextInt(6);

                // Generate Tiles
                if(random > 3)
                {
                    Game.Instantiate(new Tile(x * grassTile.getWidth(),
                            y * grassTile.getHeight(), ID.Tile, grassTile));
                }
                else
                {
                    Game.Instantiate(new Tile(x * stoneTile.getWidth(),
                            y * stoneTile.getHeight(), ID.Tile, stoneTile));
                }
            }
        }
    }
}
