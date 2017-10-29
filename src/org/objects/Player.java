package org.objects;

import org.engine.*;
import org.engine.Window;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject
{
    public Player(int x, int y)
    {
        super(x, y);
        image = SpriteLoader.getSprite("PlayerDude.png");
    }

    @Override
    public void update()
    {
        x += Input.horizontal;
        y += Input.vertical;
    }

    @Override
    public void render(Graphics2D g)
    {
        // Set the Transform for the Player
        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
        AffineTransform transform = g.getTransform();

        // Get Mouse Position
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        mouse.x /= Window.getWidth() / Renderer.getResolution().width;
        mouse.y /= Window.getHeight() / Renderer.getResolution().height;

        double xDiff = mouse.x - posX;
        double yDiff = mouse.y - posY;

        // Get Rotation to Mouse
        // angle is publicly stored in degrees
        double angle = Math.toDegrees(Math.atan2(xDiff, yDiff));

        // Rotate the Player to Mouse Cursor
        g.rotate(Math.toRadians(angle), posX, posY);

        // Draw the Player Sprite
        g.drawImage(image, at, null);

        // Set Position of Player
        g.setTransform(transform);
    }
}
