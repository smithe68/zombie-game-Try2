package org.engine.rendering;

import org.Main;
import org.engine.logic.SceneManager;

import java.awt.*;
import java.awt.image.VolatileImage;

/**
 * This class deals with rendering everything
 * onto the main window's canvas.
 *
 * @author Jakub P. Szarkowicz
 */
public class Renderer
{
    /* The default internal resolution */
    private static final int RESOLUTION = 256;

    /* The target framerate the engine runs at */
    private static final int TARGET_FPS = 60;

    /* Scaled resolution to window size */
    private static Dimension res;

    /* Variables for calculating framerate */
    private static int currentFPS;
    private static long lastFPSCheck;
    private static int totalFrames;

    /* For rendering loop's timing based on target fps */
    private static int targetTime = (int)1E9 / TARGET_FPS;

    /* Starts the main rendering loop */
    public static void startRenderer(Canvas canvas)
    {
        scaleResolution(canvas);

        Thread thread = new Thread(() ->
        {
            // Get graphics card configuration and create a frame
            GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
            VolatileImage vImage = gc.createCompatibleVolatileImage(res.width, res.height);

            while(Main.isRunning())
            {
                long startTime = System.nanoTime();

                calculateFPS();

                // Check and validate created frames
                if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                    vImage = gc.createCompatibleVolatileImage(res.width, res.height);
                }

                // Let GPU draw graphics in the canvas
                Graphics g = vImage.getGraphics();

                // Clear canvas with a flat color
                g.setColor(Color.black);
                g.fillRect(0, 0, res.width, res.height);

                // TODO - Render everything here
                if(SceneManager.getActive() != null) {
                    SceneManager.render((Graphics2D)g);
                }

                // Display framerate in the window title
                Window.setTitle("Game - FPS: " + currentFPS);

                g.dispose();

                // Cleanup volatile frames
                g = canvas.getGraphics();
                g.drawImage(vImage, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
                g.dispose();

                // Calculate frame timing
                long totalTime = System.nanoTime() - startTime;

                if(totalTime < targetTime)
                {
                    try {
                        Thread.sleep((targetTime - totalTime) / (int)1E6);
                    }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        });

        thread.setName("Rendering Thread");
        thread.start();

        System.out.println("[E]: Renderer Active");
    }

    /* Scales the resolution to the window size */
    private static void scaleResolution(Canvas canvas)
    {
        // Scale by window's width and height average
        double factor = (canvas.getWidth() + canvas.getHeight()) / 2;
        double width = canvas.getWidth() / (factor / RESOLUTION);
        double height = canvas.getHeight() / (factor / RESOLUTION);

        // Set internal resolution to new scaled resolution
        res = new Dimension((int)width, (int)height);
    }

    /* Calculates the framerate */
    private static void calculateFPS()
    {
        totalFrames++;
        if(System.nanoTime() > lastFPSCheck + (int)1E9)
        {
            lastFPSCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }
    }

    /* Returns the scaled rendering resolution */
    public static Dimension getResolution() { return res; }
}
