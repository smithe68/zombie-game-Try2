package org.engine;

public class CustomMath
{
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
}
