package org.engine.portation;

import org.engine.rendering.Renderer;
import org.engine.rendering.Window;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class EngineConfig
{
    // Retrieves the operating system's default directory e.g. Documents folder
    private static final FileSystemView SYSTEM = FileSystemView.getFileSystemView();
    private static final String DEFAULT_DIR = SYSTEM.getDefaultDirectory().getPath();
    private static final String CONFIG_PATH = DEFAULT_DIR + "/ZombieGame/Configs";

    public static void readConfig()
    {
        try
        {
            FileReader fileReader = new FileReader(CONFIG_PATH + "/Engine.ini");
            BufferedReader config = new BufferedReader(fileReader);

            String line;

            while((line = config.readLine()) != null)
            {
                String[] type = line.split(": ");
                String[] nums = type[1].split(",");

                switch(type[0])
                {
                    case "WindowSize":
                        Window.setWindowSize(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
                        break;

                    case "Fullscreen":
                        Window.setFullscreen(Boolean.valueOf(nums[0]));
                        break;

                    case "Resolution":
                        Renderer.setResolution(Integer.parseInt(nums[0]));
                        break;

                    case "Target-FPS":
                        Renderer.setTargetFPS(Integer.parseInt(nums[0]));
                        break;
                }
            }
        }
        catch(IOException e) { writeConfig(); }
    }

    private static void writeConfig()
    {
        File dir = new File(CONFIG_PATH);
        if(dir.mkdirs()) { System.out.println("[E]: Created Engine Config"); }

        try
        {
            PrintWriter writer = new PrintWriter(CONFIG_PATH + "/Engine.ini");

            writer.println("WindowSize: " + "1280" + "," + "720");
            writer.println("Resolution: " + "256" + "," + "256");
            writer.println("Target-FPS: " + "60");
            writer.println("Fullscreen: " + "false");

            writer.close();
            readConfig();
        }
        catch(IOException e) { e.printStackTrace(); }
    }
}
