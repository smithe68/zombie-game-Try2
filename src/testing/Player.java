package testing;

import java.awt.*;
import java.awt.geom.*;

public class Player extends Entity
{
    public Player(final double x, final double y) {
        super(x, y);
    }

    @Override
    public void update()
    {
        x += 0.1;
    }

    @Override
    public void render(Graphics2D g)
    {
        super.render(g);
        g.setColor(Color.red);
        g.draw(new Rectangle2D.Double(posX, posY, width, height));
    }
}
