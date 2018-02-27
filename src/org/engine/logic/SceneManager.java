package org.engine.logic;

import org.engine.components.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

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

    /* Folder where all scenes are */
    public static final String sceneFolder = "C:\\Users\\Jakub\\IdeaProjects\\RPGame\\src\\resources\\scenes";

    /* Starts the scene manager */
    public static void initialize()
    {
        scenes.add(readFromFile("Scene01.level"));
        changeScene(0);
    }

    /* Changes the main scene / level */
    public static void changeScene(int index)
    {
        currentScene = scenes.get(index);
        System.out.println("[E]: Switching to '" + scenes.get(index).sceneName + "'");
        currentScene.loadEntities();
    }

    public static Scene readFromFile(String name)
    {
        Scene scene = new Scene(name);

        try
        {
            Scanner scanner = new Scanner(new File(sceneFolder + "/" + name));

            while(scanner.hasNext())
            {
                String line = scanner.nextLine();

                if(line.contains("//") | line.isEmpty()) { continue; }

                String[] object = line.split(":");

                String[] position = object[1].split(",");
                double posX = Double.parseDouble(position[0]);
                double posY = Double.parseDouble(position[1]);

                int width = Integer.parseInt(object[2]);
                int height = Integer.parseInt(object[3]);
                int layer = Integer.parseInt(object[4]);

                Entity ent = new Entity(posX, posY);

                ent.name = object[0];

                ent.width = width;
                ent.height = height;
                ent.layer = layer;

                for(int i = 5; i < object.length; i++)
                {
                    try
                    {
                        Class<?> clazz = Class.forName("org.engine.components." + object[i]);
                        Constructor<?> ctor = clazz.getConstructor(Entity.class);
                        Object obj = ctor.newInstance(ent);

                        ent.addComponent((Component)obj);
                    }
                    catch(ClassNotFoundException e) {
                        System.err.println("Component \"" + object[i] + "\" does not exist!");
                    }
                    catch(InstantiationException | InvocationTargetException |
                            IllegalAccessException | NoSuchMethodException n) {
                        System.err.println("Cannot create \"" + object[i] + "\" component!");
                    }
                }

                scene.createEntity(ent);
            }
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }

        return scene;
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
