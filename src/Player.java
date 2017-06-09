import java.awt.*;


public class Player extends GameObject
{
    Handler handler;
    public Player(int x, int y, ID id,Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
    }
    public Rectangle getBounds()
    {
        return new Rectangle(x,y,32,32);
    }

    public void tick()
    {
        x+= velX;
        y += velY;
        x = Main.clamp(x,0,Main.WIDTH+761);
        y = Main.clamp(y,0,Main.HEIGHT+531);
        collision();

    }
    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.GetID() == ID.BasicZombie)
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH -= 2;
                }
            }

        }
    }

    public void render(Graphics g)
    {
       if (id == ID.Player) g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
