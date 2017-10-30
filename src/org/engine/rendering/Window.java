package org.engine.rendering;

import org.engine.logic.Input;
import org.engine.portation.EngineConfig;

import java.awt.*;
import java.awt.event.*;

public class Window
{
    private static boolean fullscreen;

    private static int windowWidth = 1280;
    private static int windowHeight = 720;

    private static Frame frame;
    private static Canvas canvas;

    /* Creates the Main Game Window */
    public static void initialize()
    {
        frame = new Frame();
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));

        canvas = new Canvas();
        canvas.setSize(windowWidth, windowHeight);

        EngineConfig.readConfig();

        if(fullscreen)
        {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            windowWidth = toolkit.getScreenSize().width;
            windowHeight = toolkit.getScreenSize().height;
            frame.setUndecorated(true);
        }

        frame.add(canvas);
        frame.pack();

        // Close the Game with the 'X' Button
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        canvas.requestFocus();
        canvas.transferFocus();

        Input input = new Input();
        canvas.addKeyListener(input);
        input.initialize();
    }

    /* Changes the Title of the Window */
    public static void setTitle(String title) { frame.setTitle(title); }

    /* Returns the Main Canvas */
    public static Canvas getCanvas() { return canvas; }

    /* Returns the Window's Width or Height */
    public static int getHeight() { return windowHeight; }
    public static int getWidth() { return windowWidth; }

    /* Changes the Window Size */
    public static void setWindowSize(int width, int height)
    {
        windowWidth = width;
        windowHeight = height;
    }

    /* Set if Window is Fullscreen */
    public static void setFullscreen(boolean b) { fullscreen = b; }
}
