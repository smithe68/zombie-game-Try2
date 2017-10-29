package org.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseMotionListener
{
    public static Point mousePos = new Point();

    public static float horizontal = 0;
    public static float vertical = 0;

    private static boolean[] lastKeys = new boolean[256];
    private static boolean[] currentKeys = new boolean[256];

    public static boolean getKey(int keyCode) { return currentKeys[keyCode]; }
    public static boolean getKeyDown(int keyCode) { return currentKeys[keyCode] && !lastKeys[keyCode]; }
    public static boolean getKeyUp(int keyCode) { return !currentKeys[keyCode] && lastKeys[keyCode]; }

    public void keyPressed(KeyEvent e) { currentKeys[e.getKeyCode()] = true; }
    public void keyReleased(KeyEvent e) { currentKeys[e.getKeyCode()] = false; }
    public void keyTyped(KeyEvent e) { }

    public void initialize() {
        Window.getCanvas().addMouseMotionListener(this);
    }

    public static void updateInput()
    {
        horizontal = inputAxis(KeyEvent.VK_D, KeyEvent.VK_A);
        vertical = inputAxis(KeyEvent.VK_S, KeyEvent.VK_W);
        lastKeys = currentKeys.clone();
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
}
