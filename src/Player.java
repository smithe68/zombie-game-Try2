import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
    private BufferedImage image;

    private int spriteWidth = 64;
    private int spriteHeight = 64;

    private Handler handler;

    public Player(int x, int y, ID id,Handler handler, BufferedImage image)
    {
        super(x, y, id);
        this.handler = handler;
        this.image = image;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,64,64);
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
        if (id == ID.Player)
        {
            // Draw the Player's Sprite
            g.drawImage(image, (int)x, (int)y, spriteWidth, spriteHeight, null);
        }
    }
}
