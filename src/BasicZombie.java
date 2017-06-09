import java.awt.*;

public class BasicZombie extends GameObject
{
    public BasicZombie(int x , int y, ID id)
    {
        super(x, y, id);

        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x,y,16,16);
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

        if(y <= 0 || y >= Main.screenHeight - 32)
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
        g.setColor(Color.red);
        g.fillRect(x, y, 24, 24);
    }
}
