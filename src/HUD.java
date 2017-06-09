import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by evan on 6/9/2017.
 */

public class HUD
{
    public static int HEALTH = 100;

    public void tick()
    {
        HEALTH= Main.clamp(HEALTH,0,100);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(15,15,50,12);
        g.setColor(Color.green);
        g.fillRect(15,15,HEALTH/2 ,12);
        g.setColor(Color.white);
        g.drawRect(15,15,50,12);
    }

}
