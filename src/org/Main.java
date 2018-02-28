package org;

import org.engine.components.SpriteRenderer;
import org.engine.input.Input;
import org.engine.logic.EngineConfig;
import org.engine.logic.EngineValues;
import org.engine.logic.SceneManager;
import org.engine.rendering.Renderer;
import org.engine.rendering.Updater;
import org.engine.rendering.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    /** Starts the program */
    public static void main(String[] theArgs)
    {
        System.out.println("Starting...");

        createMenuBox();
    }

    private static void createMenuBox()
    {
        setLookAndFeel();

        Frame frame = new Frame("Menu");
        frame.setPreferredSize(new Dimension(400, 500));

        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 1));

        JButton gameButton = new JButton("Game");
        JButton editorButton = new JButton("Editor");

        gameButton.addActionListener(e ->
        {
            createGameWindow();
            frame.dispose();
        });

        editorButton.addActionListener(e ->
        {
            createEditorWindow();
            frame.dispose();
        });

        panel.add(gameButton);
        panel.add(editorButton);

        frame.add(panel);
        frame.pack();

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private static void createGameWindow()
    {
        // Load Configs
        EngineValues engineValues = EngineConfig.loadConfig();

        // Create the main window
        Window mainWindow = new Window(engineValues);
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

    private static void createEditorWindow() {
        System.err.println("Not Setup");
    }

    private static void setLookAndFeel()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /* Returns if the main loops are running */
    public static boolean isRunning() {
        return engineRunning;
    }
}
