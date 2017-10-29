package org.objects;

import org.engine.logic.GameObject;
import org.engine.logic.Input;
import org.engine.portation.SpriteLoader;
import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;
import org.engine.rendering.Window;

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
        Camera.x = x;
        Camera.y = y;

        x += Input.horizontal;
        y += Input.vertical;
    }

    @Override
    public void render(Graphics2D g)
    {
        // Set the Transform for the Player
        AffineTransform transform = g.getTransform();

        double factorX = Window.getWidth() / Renderer.getResolution().width;
        double factorY = Window.getHeight() / Renderer.getResolution().height;

        double xDiff = (Input.mousePos.x / factorX) - Renderer.getResolution().width / 2;
        double yDiff = (Input.mousePos.y / factorY) - Renderer.getResolution().height / 2;

        // Get Rotation to Mouse
        rotation = (float)Math.toDegrees(Math.atan2(yDiff - y + Camera.y, xDiff - x + Camera.x));

        // Rotate the Player to Mouse Cursor
        g.rotate(Math.toRadians(rotation), posX + width / 2, posY + height / 2);

        // Draw the Player Sprite
        g.drawImage(image, (int)posX, (int)posY, width, height, null);

        // Set Position of Player
        g.setTransform(transform);
    }
}
