package org.engine.logic;

import org.engine.rendering.Renderer;
import org.engine.rendering.Window;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EngineConfig
{
    public static String getEngineFolder()
    {
        FileSystemView view = FileSystemView.getFileSystemView();
        String documents = view.getDefaultDirectory().toString();
        return documents + "/RPGame";
    }

    public static void loadConfig()
    {
        String configFolder = getEngineFolder();
        File file = new File(configFolder);
        file.mkdirs();

        try
        {
            Scanner config = new Scanner(new File(configFolder + "/Engine.cfg"));

            while(config.hasNext())
            {
                String line = config.nextLine();

                if(line.contains("[") | line.isEmpty()) { continue; }

                String[] setting = line.split(": ");

                switch(setting[0])
                {
                    case "Fullscreen":
                        Window.startFullscreen = Boolean.parseBoolean(setting[1]);
                        break;

                    case "Size":
                        String[] size = setting[1].split(",");
                        int width = Integer.parseInt(size[0]);
                        int height = Integer.parseInt(size[1]);
                        Window.setSize(width, height);
                        break;

                    case "Resolution":
                        Renderer.setResolution(Integer.parseInt(setting[1]));
                        break;

                    case "Target FPS":
                        Renderer.setTargetFPS(Integer.parseInt(setting[1]));
                        break;
                }
            }
        }
        catch(FileNotFoundException f) {
            writeConfig(configFolder);
        }
    }

    private static void writeConfig(String path)
    {
        File config = new File(path + "/Engine.cfg");

        try
        {
            PrintWriter writer = new PrintWriter(config);

            writer.println("[Window]");
            writer.println("Fullscreen: false");
            writer.println("Size: 1280,720");
            writer.println("");
            writer.println("[Renderer]");
            writer.println("Resolution: 384");
            writer.println("Target FPS: 60");

            writer.close();

            loadConfig();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
