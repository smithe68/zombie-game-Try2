package org.engine.logic;

import org.engine.rendering.Window;

import java.awt.*;
import java.awt.event.*;

public class Input implements KeyListener, MouseMotionListener, MouseWheelListener
{
    public static Point mousePos = new Point();

    public static float scrollWheel;

    public static float horizontal;
    public static float vertical;

    private static boolean[] lastKeys = new boolean[256];
    private static boolean[] currentKeys = new boolean[256];

    public static boolean getKey(int keyCode) { return currentKeys[keyCode]; }
    public static boolean getKeyDown(int keyCode) { return currentKeys[keyCode] && !lastKeys[keyCode]; }
    public static boolean getKeyUp(int keyCode) { return !currentKeys[keyCode] && lastKeys[keyCode]; }

    public void keyPressed(KeyEvent e) { currentKeys[e.getKeyCode()] = true; }
    public void keyReleased(KeyEvent e) { currentKeys[e.getKeyCode()] = false; }
    public void keyTyped(KeyEvent e) { }

    public void initialize()
    {
        Window.getCanvas().addMouseMotionListener(this);
        Window.getCanvas().addMouseWheelListener(this);
    }

    public static void updateInput()
    {
        horizontal = inputAxis(KeyEvent.VK_D, KeyEvent.VK_A);
        vertical = inputAxis(KeyEvent.VK_S, KeyEvent.VK_W);
        lastKeys = currentKeys.clone();

        if(Input.getKey(KeyEvent.VK_ESCAPE)) { System.exit(1); }
    }

    public static float inputAxis(int posKey, int negKey)
    {
        float axis = 0;
        if(getKey(posKey)) { axis = 1; }
        else if(getKey(negKey)) { axis = -1; }
        return axis;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollWheel = e.getWheelRotation();
    }
}
