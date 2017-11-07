package org.engine.rendering;

import org.engine.input.Input;

import java.awt.*;
import java.awt.event.*;

/**
 * This class creates the main window for the program.
 * It also creates a canvas where the Renderer will
 * draw graphics.
 *
 * @author Jakub P. Szarkowicz
 */
public class Window
{
    /* The window size */
    private static Dimension size;

    /* The window's frame */
    private static Frame frame;

    /* Creates the main window */
    public static Canvas createWindow()
    {
        // Set the size of the window
        size = new Dimension(800, 600);

        // Create the window frame
        frame = new Frame();
        frame.setPreferredSize(size);

        // Create the window canvas
        Canvas canvas = new Canvas();
        canvas.setSize(size);

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

        // Frame Buffering
        canvas.createBufferStrategy(3);

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
    static Dimension getSize() { return size; }
}
