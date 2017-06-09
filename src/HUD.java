import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

/**
 * Created by evan on 6/9/2017.
 */

public class HUD
{
    public Handler handler;

    public HUD (Handler handler) { this.handler = handler; }

    public static int HEALTH = 100;

    private int healthPosX;
    private int healthPosY;

    public void tick()
    {
        HEALTH = Main.clamp(HEALTH,0,100);

        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.GetID() == ID.Player)
            {
                healthPosX = tempObject.GetX() - 9;
                healthPosY = tempObject.GetY() - 20;
            }
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(healthPosX,healthPosY,50,12);
        g.setColor(Color.green);
        g.fillRect(healthPosX,healthPosY,HEALTH/2 ,12);
        g.setColor(Color.white);
        g.drawRect(healthPosX,healthPosY,50,12);
    }

}
