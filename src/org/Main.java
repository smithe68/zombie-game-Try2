package org;

import org.engine.rendering.Renderer;
import org.engine.rendering.Updater;
import org.engine.rendering.Window;

import java.awt.*;

/**
 * This class is the starting point for the program.
 * All game / engine functionality is initialized here
 * and anything else that needs to happen at the start
 * goes here as well.
 *
 * @author Jakub P. Szarkowicz
 */
public class Main
{
    /* Determines if the main loops are running */
    private static boolean engineRunning = true;

    /** Starts the program */
    public static void main(String[] theArgs)
    {
        System.out.println("Starting Program...");

        // Create the main window
        Canvas mainCanvas = Window.createWindow();

        // Start the rendering thread and the logic thread
        Renderer.startRenderer(mainCanvas);
        Updater.startUpdater();

        // Initialize everything else
    }

    /* Returns if the main loops are running */
    public static boolean isRunning() {
        return engineRunning;
    }
}
