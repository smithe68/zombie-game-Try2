package org.engine.logic;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        scenes.add(readFromFile("SceneSaveTest.level"));
        changeScene(0);
        saveSceneToFile("SceneSaveTest.level");
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

    /* Reads a .Level file as a Scene */
    public static Scene readFromFile(String name)
    {
        Scene scene = new Scene(name);

        try
        {
            String folder = EngineConfig.getEngineFolder() + "/Levels";
            Scanner scanner = new Scanner(new File(folder + "/" + name));

            while(scanner.hasNext())
            {
                String line = scanner.nextLine();

                if(line.contains("//") | line.isEmpty()) { continue; }

                String[] object = line.split(":");

                String[] position = object[2].split(",");
                double posX = Double.parseDouble(position[0]);
                double posY = Double.parseDouble(position[1]);

                int width = Integer.parseInt(object[3]);
                int height = Integer.parseInt(object[4]);
                int layer = Integer.parseInt(object[5]);

                Entity ent = new Entity(posX, posY);

                ent.name = object[0];
                ent.tag = object[1];

                ent.width = width;
                ent.height = height;
                ent.layer = layer;

                for(int i = 6; i < object.length; i++)
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
        catch(FileNotFoundException e) {
            System.err.println("Level \"" + name + "\" does not exist!");
        }

        return scene;
    }

    /* Saves current scene to a file */
    public static void saveSceneToFile(String name)
    {
        String folder = EngineConfig.getEngineFolder() + "/Levels";
        File file = new File(folder);
        file.mkdirs();

        File saveFile = new File(file + "/" + name);

        try
        {
            PrintWriter writer = new PrintWriter(saveFile);

            for(Entity ent : currentScene.entities)
            {
                writer.print(ent.name + ":");
                writer.print(ent.tag + ":");
                writer.print(ent.x + "," + ent.y + ":");
                writer.print(ent.width + ":");
                writer.print(ent.height + ":");
                writer.print(ent.layer + ":");

                LinkedList<Component> comps = ent.getComponents();

                for(int i = 0; i < comps.size(); i++)
                {
                    Component comp = comps.get(i);
                    String ending = i == comps.size() - 1 ? "" : ":";
                    writer.print(comp.getClass().getSimpleName() + ending);
                }

                writer.println("");
            }

            writer.close();
        }
        catch(IOException e) {
            System.err.println("Cannot create \"" + name + "\" file!");
        }
    }
}
