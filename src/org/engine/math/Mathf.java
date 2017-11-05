package org.engine.math;

import org.engine.logic.GameObject;
import org.engine.logic.Input;
import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;

public class Mathf
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
    public static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    public static double moveToward(double a, double b, double t) {
        return (a + Math.signum(b - a) * t);
    }

    public static double distance(double ax, double ay, double bx, double by) {
        return Math.sqrt(((ax - bx) * (ax - bx)) + ((ay - by) * (ay - by)));
    }

    public static double lookAtMouse(double ax, double ay)
    {
        double centerX = Renderer.getResolution().width / 2;
        double centerY = Renderer.getResolution().height / 2;

        // Get Difference Between Mouse Position and Center of Screen
        double xDiff = (Input.mousePos.x / Renderer.getResolutionFactor().width) - centerX;
        double yDiff = (Input.mousePos.y / Renderer.getResolutionFactor().height) - centerY;

        // Calculate Rotation in Degrees from Player to Mouse Position
        return Math.atan2(yDiff - ay + Camera.y, xDiff - ax + Camera.x);
    }

    public static double lookAt(GameObject a, GameObject b) {
        return Math.atan2(b.posY - a.posY, b.posX - a.posX);
    }

    public static double lookAt(double ax, double ay, double bx, double by) {
        return Math.atan2(by - ay, bx - ax);
    }
}
