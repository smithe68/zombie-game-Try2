import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicZombie extends GameObject
{
    public ZombieState currentState = ZombieState.Wandering;

    private BufferedImage image;
    private GameObject player;

    public BasicZombie(int x , int y, ID id, BufferedImage image, GameObject player)
    {
        super(x, y, id);

        this.image = image;
        this.player = player;

        velX += 1;
        velY += 1;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y,64,64);
    }

    public enum ZombieState
    {
        Wandering,
        Following,
        Attacking
    }

    public void tick()
    {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.screenHeight -50 )
        {
            velY *= -1;
        }

        if(x <= 0 || x >= Main.screenWidth - 32)
        {
            velX *= -1;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(image, x, y, 64, 64, null);
    }
}
