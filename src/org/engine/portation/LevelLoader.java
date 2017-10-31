package org.engine.portation;

import org.engine.logic.GameObject;
import org.engine.logic.Level;
import org.objects.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class LevelLoader
{
    // Retrieves the operating system's default directory e.g. Documents folder
    private static final FileSystemView SYSTEM = FileSystemView.getFileSystemView();
    private static final String DEFAULT_DIR = SYSTEM.getDefaultDirectory().getPath();
    private static final String SAVE_PATH = DEFAULT_DIR + "/ZombieGame/Levels";

    /* Loads a Level from a File */
    public static void load(String name)
    {
        createSaveFolder();

        try
        {
            FileReader fileReader = new FileReader(SAVE_PATH + "/" + name);
            BufferedReader config = new BufferedReader(fileReader);

            String line;

            while((line = config.readLine()) != null)
            {
                String[] type = line.split(": ");
                String[] nums = type[1].split(",");

                switch(type[0])
                {
                    case "Player":
                        Level.instantiate(new Player(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
                        break;

                    case "Zombie":
                        Level.instantiate(new Zombie(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
                        break;
                }
            }
        }
        catch(IOException e) { System.out.println("Cannot Read File: " + name); }
    }

    public static void saveLevel(String name)
    {
        try
        {
            PrintWriter writer = new PrintWriter(SAVE_PATH + "/" + name);

            for(int i = 0; i < Level.objects.size(); i++)
            {
                GameObject object = Level.objects.get(i);
                writer.println(object.name + ": " + object.x + "," + object.y);
            }

            writer.close();
        }
        catch(IOException e) { e.printStackTrace(); }
    }

    private static void createSaveFolder()
    {
        File dir = new File(SAVE_PATH);
        if(dir.mkdirs()) { System.out.println("[E]: Save Directory Created"); }
    }
}
