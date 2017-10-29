package org.engine.logic;

import java.awt.*;
import java.util.ArrayList;

public class LevelManager
{
    private static Level currentLevel;

    private static ArrayList<Level> levels = new ArrayList<>();

    /* Creates all the Levels and Loads the First */
    public static void initialize()
    {
        levels.add(new Level());
        loadLevel(0);
    }

    /* Loads the Level at Index */
    public static void loadLevel(int index)
    {
        if(currentLevel != null)
            currentLevel.clearLevel();

        currentLevel = levels.get(index);
        currentLevel.loadObjects();
    }

    /* Updates the Current Loaded Level */
    public static void update() { currentLevel.update(); }

    /* Renders the Current Loaded Level */
    public static void render(Graphics g) { currentLevel.render(g); }
}
