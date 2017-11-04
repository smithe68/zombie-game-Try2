package org.engine.logic;

import org.engine.rendering.Camera;
import org.engine.rendering.Renderer;

public class Updater
{
    public static final double DELTA_TIME = 0.025;

    private static int targetTime = (int)1E9 / Renderer.getTargetFPS();
    private static long lastTime = System.nanoTime();

    /* Starts the Logic / Update Loop */
    public static void initialize()
    {
        Thread thread = new Thread(() ->
        {
            while(Renderer.getRunning())
            {
                long startTime = System.nanoTime();

                // Calculate a Delta Time
                lastTime = System.nanoTime();

                // Update Stuff
                LevelManager.update();
                Input.updateInput();
                Camera.update();

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

        thread.setName("Update Thread");
        thread.start();
    }
}
