package org.engine;

public class CustomMathf
{
    public static float NineAxisRotation(float rotValue, float hori, float vert, float speed, float deltaTime)
    {
        deltaTime = deltaTime * speed;

        // RIGHT

        if(hori > 0 && vert == 0)
        {
            rotValue = Renderer.Lerp(rotValue, 0, deltaTime);
        }

        if(hori > 0 && vert > 0)
        {
            rotValue = Renderer.Lerp(rotValue, -45, deltaTime);
        }

        if(hori > 0 && vert < 0)
        {
            rotValue = Renderer.Lerp(rotValue, 45, deltaTime);
        }

        // LEFT

        if(hori < 0 && vert == 0)
        {
            rotValue = Renderer.Lerp(rotValue, 180, deltaTime);
        }

        if(hori < 0 && vert > 0)
        {
            rotValue = Renderer.Lerp(rotValue, 225, deltaTime);
        }

        if(hori < 0 && vert < 0)
        {
            rotValue = Renderer.Lerp(rotValue, 135, deltaTime);
        }

        // UP

        if(vert > 0 && hori == 0)
        {
            rotValue = Renderer.Lerp(rotValue, -90, deltaTime);
        }

        if(vert > 0 && hori > 0)
        {
            rotValue = Renderer.Lerp(rotValue, -45, deltaTime);
        }

        if(vert > 0 && hori < 0)
        {
            rotValue = Renderer.Lerp(rotValue, 225, deltaTime);
        }

        // DOWN

        if(vert < 0 && hori == 0)
        {
            rotValue = Renderer.Lerp(rotValue, 90, deltaTime);
        }

        if(vert < 0 && hori > 0)
        {
            rotValue = Renderer.Lerp(rotValue, 45, deltaTime);
        }

        if(vert < 0 && hori < 0)
        {
            rotValue = Renderer.Lerp(rotValue, 135, deltaTime);
        }

        return rotValue;
    }
}
