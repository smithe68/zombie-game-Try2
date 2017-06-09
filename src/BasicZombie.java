import java.awt.Graphics;
import java.awt.Color;

public class BasicZombie extends GameObject
{
    public BasicZombie(int x , int y, ID id)
    {
        super(x, y, id);

        velX = 5;
        velY = 5;
    }

    public void tick()
    {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.screenHeight - 32)
        {
            velY *= -1;
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
