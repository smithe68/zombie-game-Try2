import java.awt.*;
import java.util.LinkedList;

public class Handler
{
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public GameObject AddObject(GameObject object)
    {
        this.object.add(object);

        return object;
    }

    public void RemoveObject(GameObject object)
    {
        this.object.remove(object);
    }
}
