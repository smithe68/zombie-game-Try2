package org.engine.rendering;

import org.engine.input.Input;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class creates the main window for the program.
 * It also creates a canvas where the Renderer will
 * draw graphics.
 * @author Jakub P. Szarkowicz
 */
public class Window
{
    /* The window size */
    private static Dimension size;

    /* The window's frame */
    private static Frame frame;

    /* If window is Fullscreen */
    public static boolean startFullscreen;

    /* Creates the main window */
    public static Canvas createWindow()
    {
        if(size == null)
        {
            // Set the size of the window
            size = new Dimension(800, 600);
        }

        // Create the window frame
        frame = new Frame();
        frame.setPreferredSize(size);

        // Create the window canvas
        Canvas canvas = new Canvas();
        canvas.setSize(size);

        setFullscreen(startFullscreen);

        // Close window when 'X' is pressed
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        frame.add(canvas);
        frame.pack();

        // Set frame attributes
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Make OS focus on window
        canvas.requestFocus();
        canvas.transferFocus();

        // Adds a key listener to the canvas
        canvas.addKeyListener(new Input());

        return canvas;
    }

    /* Set the window's title */
    static void setTitle(String title)
    {
        if(frame != null) {
            frame.setTitle(title);
        }
    }

    /* Return window's size */
    public static Dimension getSize() { return size; }

    /* Sets the Size of the Window */
    public static void setSize(int width, int height) {
        size = new Dimension(width, height);
    }

    public static void setFullscreen(boolean fullscreen)
    {
        frame.setUndecorated(fullscreen);

        if(fullscreen)
        {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            frame.setSize(toolkit.getScreenSize());
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
        else
        {
            frame.setSize(size);
            frame.setExtendedState(Frame.NORMAL);
        }
    }
}
