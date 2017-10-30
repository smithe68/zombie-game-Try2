package org.engine.logic;

public class CustomMath
{
    // Constricts a Value to a Min and a Max
    public static double clamp(double var, double min, double max)
    {
        if(var>= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    // Smoothly Move one Value to Another
    public static double lerp(double a, double b, double t)
    {
        return a + t * (b - a);
    }

    public static double moveToward(double a, double b, double t)
    {
        return (a + Math.signum(b - a) * t);
    }
}
