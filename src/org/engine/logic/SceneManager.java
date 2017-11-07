package org.engine.logic;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class holds all scenes that are in the game.
 * It also manages scenes and is in charge for switching
 * scenes.
 *
 * @author Jakub P. Szarkowicz
 */
public class SceneManager
{
    /* The current scene that is active */
    private static Scene currentScene;

    /* Stores all the scenes */
    private static ArrayList<Scene> scenes = new ArrayList<>();

    /* Starts the scene manager */
    public static void initialize()
    {
        scenes.add(new Scene("Test Scene"));
        changeScene(0);
    }

    /* Changes the main scene / level */
    public static void changeScene(int index)
    {
        currentScene = scenes.get(index);
        System.out.println("[E]: Switching to '" + scenes.get(index).sceneName + "'");
        currentScene.loadEntities();
    }

    /* Updates the current scene */
    public static void update() {
        if(currentScene != null) currentScene.update();
    }

    /* Renders the current scene */
    public static void render(Graphics2D g) {
        if(currentScene != null) currentScene.render(g);
    }

    /* Returns the active scene */
    public static Scene getActive() { return currentScene; }
}
