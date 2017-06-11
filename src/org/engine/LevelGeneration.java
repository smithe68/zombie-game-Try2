package org.engine;

import org.objects.BasicZombie;
import org.objects.Player;
import org.objects.Tile;
import org.ui.HUD;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class LevelGeneration
{
    public LevelGeneration()
    {
        try
        {
            // Get Sprites for Generation
            stoneTile = Renderer.LoadImage("/resources/sprites/StoneTile.png");
            grassTile = Renderer.LoadImage("/resources/sprites/GrassTile.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public int levelWidth = 20, levelHeight = 20;

    private BufferedImage stoneTile;
    private BufferedImage grassTile;

    public void CreateLevel()
    {
        for(int x = 0; x < levelWidth; x++)
        {
            for(int y = 0; y < levelHeight; y++)
            {
                Game.Instantiate(new Tile(x * stoneTile.getWidth(), y * stoneTile.getHeight(), ID.Tile, grassTile));
            }
        }
    }
}
