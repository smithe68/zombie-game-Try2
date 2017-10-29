package org.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener
{
    private static boolean[] lastKeys = new boolean[256];
    private static boolean[] currentKeys = new boolean[256];

    public static float horizontal = 0;
    public static float vertical = 0;

    public static boolean getKey(int keyCode) { return currentKeys[keyCode]; }
    public static boolean getKeyDown(int keyCode) { return currentKeys[keyCode] && !lastKeys[keyCode]; }
    public static boolean getKeyUp(int keyCode) { return !currentKeys[keyCode] && lastKeys[keyCode]; }

    public void keyPressed(KeyEvent e) { currentKeys[e.getKeyCode()] = true; }
    public void keyReleased(KeyEvent e) { currentKeys[e.getKeyCode()] = false; }
    public void keyTyped(KeyEvent e) { }

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
}
