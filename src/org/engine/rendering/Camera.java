package org.engine.rendering;

import org.engine.logic.Input;

public class Camera
{
    // Camera Position
    public static double x, y;
    public static int zoom = 1;

    private static final int ZOOM_MIN = 1;
    private static final int ZOOM_MAX = 3;

    public static void update()
    {
        if(Input.scrollWheel == 1)
        {
            if(zoom < ZOOM_MAX)
                zoom += 1;
        }

        if(Input.scrollWheel == -1)
        {
            if(zoom > ZOOM_MIN)
                zoom -= 1;
        }
    }

    public static void setPosition(double newX, double newY)
    {
        x = newX;
        y = newY;
    }
}
