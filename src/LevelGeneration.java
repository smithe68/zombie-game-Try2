import java.awt.image.BufferedImage;

public class LevelGeneration
{
    public LevelGeneration(Handler handler, BufferedImage stoneTile)
    {
        this.handler = handler;
        this.stoneTile = stoneTile;
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
