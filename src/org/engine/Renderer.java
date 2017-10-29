package org.engine;

import java.awt.*;
import java.awt.image.VolatileImage;

public class Renderer
{
    private static boolean running = true;

    private static final int RESOLUTION = 384;
    private static final int TARGET_FPS = 60;

    private static int resolutionWidth;
    private static int resolutionHeight;

    private static int currentFPS;
    private static long lastFpsCheck;
    private static int totalFrames;

    private static int targetTime = (int)1E9 / TARGET_FPS;

    /* Starts the Rendering Loop */
    public static void initialize()
    {
        // Scale Resolution to Window Size
        int factor = Window.getWidth() / RESOLUTION;
        resolutionWidth = Window.getWidth() / factor;
        resolutionHeight = Window.getHeight() / factor;

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
                        Thread.sleep((targetTime - totalTime) / 1000000);
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

    /* Calculates the Average FPS */
    private static void calculateFPS()
    {
        // FPS Counter
        totalFrames++;
        if(System.nanoTime() > lastFpsCheck + 1000000000)
        {
            lastFpsCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }
    }

    /* Return whether or not the Renderer is Running */
    public static boolean getRunning() { return running; }

    /* Return the Renderer Target FPS */
    public static int getTargetFPS() { return TARGET_FPS; }

    /* Return the Game Internal Resolution */
    public static Dimension getResolution() {
        return new Dimension(resolutionWidth, resolutionHeight);
    }
}
