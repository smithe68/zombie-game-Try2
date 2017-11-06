package org.engine.rendering;

import org.Main;

/**
 * This class deals with the update / logic
 * loop for the game. No graphics are included
 * with this loop.
 *
 * @author Jakub P. Szarkowicz
 */
public class Updater
{
    /* Rate at which logic is updated */
    private static final int TARGET_UPS = 60;

    /* Logic loop timing */
    private static int targetTime = (int)1E9 / TARGET_UPS;

    /* Starts the updater / logic main loop */
    public static void startUpdater()
    {
        Thread thread = new Thread(() ->
        {
            while(Main.isRunning())
            {
                long startTime = System.nanoTime();

                // TODO - Do all updating / logic here

                // Calculate update timing
                long totalTime = System.nanoTime() - startTime;

                if(totalTime < targetTime)
                {
                    try {
                        Thread.sleep((targetTime - totalTime) / (int)1E6);
                    }
                    catch(InterruptedException e) { e.printStackTrace(); }
                }
            }
        });

        thread.setName("Update Thread");
        thread.start();
    }
}
