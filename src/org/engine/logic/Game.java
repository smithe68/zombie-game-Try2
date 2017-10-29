package org.engine.logic;

import org.engine.rendering.Renderer;
import org.engine.rendering.Window;

public class Game
{
    /* Starts the Game and Initializes Important Stuff */
    public static void main(String[] theArgs)
    {
        // Create the Main Window
        Window.initialize();

        // Initialize Level System
        LevelManager.initialize();

        // Start Main Game Loops
        Renderer.initialize();
        Updater.initialize();
    }
}
