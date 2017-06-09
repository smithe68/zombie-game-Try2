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

            if(tempObject.getId() == ID.Player) {

                if (key == KeyEvent.VK_W) tempObject.setVelY(-5);
                if (key == KeyEvent.VK_W) tempObject.setVelY(5);
                if (key == KeyEvent.VK_W) tempObject.setVelX(5);
                if (key == KeyEvent.VK_W) tempObject.setVelX(-5);
            }
        }

    }
    public void keyReleased (KeyEvent e)
    {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {

                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_W) tempObject.setVelX(0);
                if (key == KeyEvent.VK_W) tempObject.setVelX(0);
            }
        }

    }

}
