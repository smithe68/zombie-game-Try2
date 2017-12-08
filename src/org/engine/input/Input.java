package org.engine.input;

import java.awt.event.*;
import java.util.HashMap;

/**
 * This class deals with detecting and registering
 * keyboard and mouse input from the user.
 * @author Jakub P. Szarkowicz
 */
public class Input implements KeyListener
{
    private static boolean[] currentKeys = new boolean[256];
    private static boolean[] lastKeys = new boolean[256];

    private static HashMap<String, KeyContainer> keyAxis = new HashMap<>();

    public static void initialize()
    {
        keyAxis.put("Horizontal", new KeyContainer(KeyEvent.VK_D, KeyEvent.VK_A));
        keyAxis.put("Vertical", new KeyContainer(KeyEvent.VK_W, KeyEvent.VK_S));
    }

    public static boolean getKey(int keycode) { return currentKeys[keycode]; }

    public static boolean getKeyDown(int keycode) {
        return currentKeys[keycode] && !lastKeys[keycode];
    }

    public static boolean getKeyUp(int keycode) {
        return !currentKeys[keycode] && lastKeys[keycode];
    }

    /* Finishes any input and deals with input axis */
    public static void finishInput() {
        lastKeys = currentKeys.clone();
    }

    public static int getInputAxis(String axisName)
    {
        if(!keyAxis.containsKey(axisName))
        {
            System.err.println("[ERROR]: Axis " + axisName + " does not exist!");
            return 0;
        }

        int axis = 0;
        if(getKey(keyAxis.get(axisName).posKey)) { axis = 1; }
        if(getKey(keyAxis.get(axisName).negKey)) { axis = -1; }
        return axis;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        currentKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentKeys[e.getKeyCode()] = false;
    }
}

class KeyContainer
{
    int posKey;
    int negKey;

    KeyContainer(int pos, int neg)
    {
        this.posKey = pos;
        this.negKey = neg;
    }
}
