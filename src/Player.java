import java.awt.Graphics;
import java.awt.Color;


public class Player extends GameObject
{
    public Player(int x, int y, ID id)
    {
        super(x, y, id);
    }

    public void tick()
    {
        x+= velX;
        y += velY;
        x = Main.clamp(x,0,Main.WIDTH+761);
        y = Main.clamp(y,0,Main.HEIGHT+531);

    }

    public void render(Graphics g)
    {
       if (id == ID.Player) g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
