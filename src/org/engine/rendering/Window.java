package org.engine.rendering;

import org.engine.input.Input;
import org.engine.logic.EngineValues;

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
    private Dimension size;

    /* The window's frame */
    private Frame frame;

    /* The window's canvas */
    public Canvas canvas;

    /* If window is Fullscreen */
    public boolean startFullscreen;

    /* Creates the main window */
    public Window(EngineValues vals)
    {
        // Set Starting Values
        this.size = vals.windowSize;
        this.startFullscreen = vals.fullscreen;

        // Create the window frame
        frame = new Frame();
        frame.setPreferredSize(size);

        // Create the window canvas
        canvas = new Canvas();
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
    }

    /* Set the window's title */
    void setTitle(String title)
    {
        if(frame != null) {
            frame.setTitle(title);
        }
    }

    /* Return window's size */
    public Dimension getSize() { return size; }

    /* Sets the Size of the Window */
    public void setSize(int width, int height) {
        size = new Dimension(width, height);
    }

    /* Sets if Window is Fullscreen */
    public void setFullscreen(boolean fullscreen)
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
