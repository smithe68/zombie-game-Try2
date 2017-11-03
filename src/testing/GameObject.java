package testing;

import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;

import java.awt.*;
import java.awt.geom.*;

public interface GameObject
{
    void update();
    void render(Graphics2D g);

    /* Returns the GameObject's Bounds */
    default Rectangle2D.Double getBounds(Entity e) {
        return new Rectangle2D.Double(e.x, e.y, e.width, e.height);
    }

    /* Renders the GameObject relative to Camera */
    default void renderTransform(Entity e)
    {
        e.posX = (e.x - e.width / 2) - (Camera.x + Renderer.getResolution().width / 2);
        e.posY = (e.y - e.height / 2) - (Camera.y + Renderer.getResolution().height / 2);
    }
}
