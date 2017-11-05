package org.engine.math;

public class Vector
{
    public double x;
    public double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double magnitude() {
        return Math.sqrt((x * x) + (y * y));
    }

    public void normalize()
    {
        double mag = magnitude();

        if(mag > 0)
        {
            x /= mag;
            y /= mag;
        }
    }
}
