package org.engine;

public class Updater
{
    public static float deltaTime;

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
                deltaTime = (System.nanoTime() - lastTime) / (int)1E9;
                lastTime = System.nanoTime();

                // Update Stuff
                LevelManager.update();
                Input.updateInput();

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
