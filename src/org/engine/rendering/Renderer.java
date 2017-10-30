package org.engine.rendering;

import org.engine.logic.LevelManager;

import java.awt.*;
import java.awt.image.VolatileImage;

public class Renderer
{
    private static boolean running = true;

    private static int resolution = 256;
    private static int targetFPS = 60;

    private static int resolutionWidth;
    private static int resolutionHeight;

    private static int resFactorX;
    private static int resFactorY;

    private static int currentFPS;
    private static long lastFpsCheck;
    private static int totalFrames;

    private static int targetTime = (int)1E9 / targetFPS;

    /* Starts the Rendering Loop */
    public static void initialize()
    {
        scaleResolution();

        Thread thread = new Thread(() ->
        {
            GraphicsConfiguration gc = Window.getCanvas().getGraphicsConfiguration();
            VolatileImage vImage = gc.createCompatibleVolatileImage(resolutionWidth, resolutionHeight);

            while(running)
            {
                long startTime = System.nanoTime();
                calculateFPS();

                if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE)
                    vImage = gc.createCompatibleVolatileImage(resolutionWidth, resolutionHeight);

                Graphics g = vImage.getGraphics();

                // Clear the Screen
                g.setColor(Color.black);
                g.fillRect(0, 0, resolutionWidth, resolutionHeight);

                // Render Stuff
                LevelManager.render(g);

                // Display FPS on the Window Title
                Window.setTitle("Zombie Game - FPS: " + currentFPS);

                g.dispose();

                g = Window.getCanvas().getGraphics();
                g.drawImage(vImage, 0, 0, Window.getWidth(), Window.getHeight(), null);

                g.dispose();

                long totalTime = System.nanoTime() - startTime;

                if(totalTime < targetTime)
                {
                    try {
                        Thread.sleep((targetTime - totalTime) / (int)1E6);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.setName("Rendering Thread");
        thread.start();
    }

    /* Scales the Resolution to Window Size */
    private static void scaleResolution()
    {
        // Scale Resolution to Window Size
        int factor = Window.getWidth() / resolution;

        resolutionWidth = Window.getWidth() / factor;
        resolutionHeight = Window.getHeight() / factor;

        resFactorX = Window.getWidth() / resolutionWidth;
        resFactorY = Window.getHeight() / resolutionHeight;
    }

    /* Calculates the Average FPS */
    private static void calculateFPS()
    {
        // FPS Counter
        totalFrames++;
        if(System.nanoTime() > lastFpsCheck + (int)1E9)
        {
            lastFpsCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }
    }

    /* Return whether or not the Renderer is Running */
    public static boolean getRunning() { return running; }

    /* Return the Renderer Target FPS */
    public static int getTargetFPS() { return targetFPS; }

    /* Return the Game Internal Resolution */
    public static Dimension getResolution() {
        return new Dimension(resolutionWidth, resolutionHeight);
    }

    /* Sets the Default Internal Resolution */
    public static void setDefaultResolution(int res) { resolution = res; }

    /* Returns the Factor at which the Resolution in Scaled */
    public static Dimension getResolutionFactor() {
        return new Dimension(resFactorX, resFactorY);
    }

    /* Set the Game's Target FPS */
    public static void setTargetFPS(int fps) { targetFPS = fps; }
}
