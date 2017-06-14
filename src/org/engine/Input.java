package org.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener
{
    private static boolean[] lastKeys = new boolean[196];
    private static boolean[] currentKeys = new boolean[196];

    // Input Axis
    public static float Horizontal = 0;
    public static float Vertical = 0;

    public static boolean GetKey(int keyCode)
    {
        // Set Boolean of Each Key
        return currentKeys[keyCode];
    }

    public static boolean GetKeyDown(int keyCode)
    {
        // Check once for Key Input
        return currentKeys[keyCode] && !lastKeys[keyCode];
    }

    public static boolean GetKeyUp(int keyCode)
    {
        // Check if a Key is Released
        return !currentKeys[keyCode] && lastKeys[keyCode];
    }

    public static void UpdateInput()
    {
        Horizontal = InputAxis(KeyEvent.VK_D, KeyEvent.VK_A);
        Vertical = InputAxis(KeyEvent.VK_S, KeyEvent.VK_W);

        lastKeys = currentKeys.clone();
    }

    public void keyPressed(KeyEvent e)
    {
        // Check for Key Press
        currentKeys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e)
    {
        // Check for Key Release
        currentKeys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public static float InputAxis(int posKey, int negKey)
    {
        float axis;

        if(GetKey(posKey)) { axis = 1; }
        else if(GetKey(negKey)) { axis = -1; }
        else { axis = 0; }

        return axis;
    }
}
