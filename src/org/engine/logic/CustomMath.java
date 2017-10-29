package org.engine.logic;

public class CustomMath
{
    // Constricts a Value to a Min and a Max
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

    // Smoothly Move one Value to Another
    public static float Lerp(float a, float b, float t)
    {
        return a + t * (b - a);
    }
}
