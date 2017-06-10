import java.awt.*;
import java.awt.image.BufferedImage;

public class Follower extends GameObject
{
    public ZombieState currentState = ZombieState.Wandering;

    private Handler handler;
    private BufferedImage image;
    private GameObject player;

    public Follower(int x , int y, ID id, BufferedImage image, GameObject player, Handler handler)
    {
        super(x, y, id);

        this.image = image;
        this.player = player;
        this.handler = handler;

        for(int i = 0; i < handler.object.size();i++)
        {
            if (handler.object.get(i).GetID() == ID.Player) player = handler.object.get(i);
        }



    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y,64,64);
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

        float diffX = x - player.GetX() + 9;
        float diffY = y - player.GetY() + 9;
        float distance = (float) (Math.sqrt((x-player.GetX())*(x-player.GetX()) + (y-player.GetY()) * y-player.GetY()));
        if(y <= 0 || y >= Main.screenHeight -50 )

        velX = (float) ((-1.0/distance)*diffX);
        velY = (float) ((-1.0/distance)*diffY);
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
        g.drawImage(image, (int)x,(int) y, 64, 64, null);
    }
}
