package org.engine;

import org.objects.GameObject;
import org.world.World;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Vector;

public class CustomMathf
{
    public static float NineAxisRotation(float rotValue, float hori, float vert, float speed, float deltaTime)
    {
        deltaTime = deltaTime * speed;

        // RIGHT

        if(hori > 0 && vert == 0)
        {
            rotValue = Lerp(rotValue, 0, deltaTime);
        }

        if(hori > 0 && vert > 0)
        {
            rotValue = Lerp(rotValue, -45, deltaTime);
        }

        if(hori > 0 && vert < 0)
        {
            rotValue = Lerp(rotValue, 45, deltaTime);
        }

        // LEFT

        if(hori < 0 && vert == 0)
        {
            rotValue = Lerp(rotValue, 180, deltaTime);
        }

        if(hori < 0 && vert > 0)
        {
            rotValue = Lerp(rotValue, 225, deltaTime);
        }

        if(hori < 0 && vert < 0)
        {
            rotValue = Lerp(rotValue, 135, deltaTime);
        }

        // UP

        if(vert > 0 && hori == 0)
        {
            rotValue = Lerp(rotValue, -90, deltaTime);
        }

        if(vert > 0 && hori > 0)
        {
            rotValue = Lerp(rotValue, -45, deltaTime);
        }

        if(vert > 0 && hori < 0)
        {
            rotValue = Lerp(rotValue, 225, deltaTime);
        }

        // DOWN

        if(vert < 0 && hori == 0)
        {
            rotValue = Lerp(rotValue, 90, deltaTime);
        }

        if(vert < 0 && hori > 0)
        {
            rotValue = Lerp(rotValue, 45, deltaTime);
        }

        if(vert < 0 && hori < 0)
        {
            rotValue = Lerp(rotValue, 135, deltaTime);
        }

        return rotValue;
    }

    public static int randInt(int min, int max)
    {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static float Clamp(float var, float min, float max)
    {
        if(var>= max)
        {
            return var = max;
        }
        else if (var <= min)
        {
            return var = min;
        }
        else
        {
            return var;
        }
    }

    public static float Lerp(float a, float b, float t)
    {
        return a + t * (b - a);
    }

    public static float Slerp(float a, float b, float t)
    {
        float dot = a * b;
        dot = Clamp(dot, -1.0f, 1.0f);
        float theta = (float)(Math.acos(dot) * t);
        float relative = b - a * dot;
        float normalized = (relative - (-1))/((-1)-1);
        return (float)((a * Math.cos(theta)) + ((normalized * Math.sin(theta))));
    }

    public static GameObject Linecast(Point start, Point end)
    {
        Point diff = new Point(end.x - start.x, end.y - start.y);

        float lineX;
        float lineY;

        for(int x = 0; x < diff.x; x++)
        {
            lineX = start.x + x;

            for(int y = 0; y < diff.x; y++)
            {
                lineY = start.y + y;

                Rectangle rect = new Rectangle((int)lineX, (int)lineY, 1, 1);

                for(int i = 0; i < World.gameObjects.size(); i++)
                {
                    if(rect.intersects(World.gameObjects.get(i).getBounds()))
                    {
                        return World.gameObjects.get(i);
                    }
                }
            }
        }

        return null;
    }

    public static Vector2D Normalize(Vector2D a)
    {
        float length = (float)(Math.sqrt((a.x * a.x) + (a.y * a.y)));

        a.x = a.x/Math.abs(length);
        a.y = a.y/Math.abs(length);

        return a;
    }

    public static double CalculateAngle(double x1, double y1, double x2, double y2)
    {
        double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
        // Keep angle between 0 and 360
        angle = angle + Math.ceil( -angle / 360 ) * 360;

        return angle;
    }

    public double AngleInRelation(int x1, int y1, int x2, int y2)
    {
        // Point 1 in relation to point 2
        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);

        int xdiff = Math.abs(point2.x - point1.x);
        int ydiff = Math.abs(point2.y - point1.y);

        double deg = 361;

        if ( (point2.x > point1.x) && (point2.y < point1.y) )
        {
            // Quadrant 1
            deg = -Math.toDegrees(Math.atan(Math.toRadians(ydiff) / Math.toRadians(xdiff)));
        }
        else if ( (point2.x > point1.x) && (point2.y > point1.y) )
        {
            // Quadrant 2
            deg = Math.toDegrees(Math.atan(Math.toRadians(ydiff) / Math.toRadians(xdiff)));
        }
        else if ( (point2.x < point1.x) && (point2.y > point1.y) )
        {
            // Quadrant 3
            deg = 90 + Math.toDegrees(Math.atan(Math.toRadians(xdiff) / Math.toRadians(ydiff)));
        }
        else if ( (point2.x < point1.x) && (point2.y < point1.y) )
        {
            // Quadrant 4
            deg = 180 + Math.toDegrees(Math.atan(Math.toRadians(ydiff) / Math.toRadians(xdiff)));
        }
        else if ((point2.x == point1.x) && (point2.y < point1.y))
        {
            deg = -90;
        }
        else if ((point2.x == point1.x) && (point2.y > point1.y))
        {
            deg = 90;
        }
        else if ((point2.y == point1.y) && (point2.x > point1.x))
        {
            deg = 0;
        }
        else if ((point2.y == point2.y) && (point2.x < point1.x))
        {
            deg = 180;
        }
        if (deg == 361)
        {
            deg = 0;
        }

        return deg;
    }
}
