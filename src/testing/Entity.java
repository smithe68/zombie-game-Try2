package testing;

import java.awt.*;

public class Entity implements GameObject
{
    public double x, y;
    public double posX, posY;
    public int width, height;

    public Entity(final double x, final double y)
    {
        this.x = x;
        this.y = y;

        this.width = 16;
        this.height = 16;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        renderTransform(this);
    }
}
