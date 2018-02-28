package org;

import org.engine.components.SpriteRenderer;
import org.engine.input.Input;
import org.engine.logic.EngineConfig;
import org.engine.logic.EngineValues;
import org.engine.logic.SceneManager;
import org.engine.rendering.Renderer;
import org.engine.rendering.Updater;
import org.engine.rendering.Window;

import java.awt.*;

/**
 * This class is the starting point for the program.
 * All game / engine functionality is initialized here
 * and anything else that needs to happen at the start
 * goes here as well.
 * @author Jakub P. Szarkowicz
 */
public class Main
{
    /* Determines if the main loops are running */
    private static boolean engineRunning = true;

    private static Window mainWindow;
    private static EngineValues engineValues;

    /** Starts the program */
    public static void main(String[] theArgs)
    {
        System.out.println("Starting...");

        // Load Configs
        engineValues = EngineConfig.loadConfig();

        // Create the main window
        mainWindow = new Window(engineValues);
        Canvas mainCanvas = mainWindow.canvas;

        // Setup Sprite Renderer
        SpriteRenderer.setGraphicsConfiguration(mainCanvas);

        // Start the rendering thread and the logic thread
        Renderer.startRenderer(mainWindow, engineValues);
        Updater.startUpdater();

        // Initialize everything else
        Input.initialize();
        SceneManager.initialize();
    }

    /* Returns if the main loops are running */
    public static boolean isRunning() {
        return engineRunning;
    }
}
