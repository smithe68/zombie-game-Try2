import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }

    public void keyPressed (KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.GetID() == ID.Player) {

                if (key == KeyEvent.VK_W) tempObject.SetVelY(-5);
                if (key == KeyEvent.VK_S) tempObject.SetVelY(5);
                if (key == KeyEvent.VK_D) tempObject.SetVelX(5);
                if (key == KeyEvent.VK_A) tempObject.SetVelX(-5);
            }
        }

    }
    public void keyReleased (KeyEvent e)
    {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.GetID() == ID.Player) {

                if (key == KeyEvent.VK_W) tempObject.SetVelY(0);
                if (key == KeyEvent.VK_S) tempObject.SetVelY(0);
                if (key == KeyEvent.VK_D) tempObject.SetVelX(0);
                if (key == KeyEvent.VK_A) tempObject.SetVelX(0);
            }
        }

    }

}
