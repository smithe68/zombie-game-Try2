package testing;

import org.engine.components.Component;
import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;

import java.awt.*;
import java.util.ArrayList;

public class GameObject
{
    protected int x, y;
    protected int posX, posY;

    protected int height = 16;
    protected int width = 16;

    protected double rotation;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    protected void update() { }

    protected void render(Graphics2D g) {
        renderTransform();
    }

    private void renderTransform()
    {
        // Get Game's Internal Resolution
        int resWidth = Renderer.getResolution().width;
        int resHeight = Renderer.getResolution().height;

        // Set the Rendering Position
        posX = (x - width / 2) - (int)Camera.x + resWidth / 2;
        posY = (y - height / 2) - (int)Camera.y + resHeight / 2;
    }

    protected <T extends Component> T addComponent(T c)
    {
        components.add(c);
        return c;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }
}
