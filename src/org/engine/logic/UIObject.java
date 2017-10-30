package org.engine.logic;

import java.awt.event.*;

public class UIObject extends GameObject implements MouseListener, MouseMotionListener
{
    protected boolean containsMouse;

    public UIObject(double x, double y)
    {
        super(x, y);
        isPersistant = true;
    }

    @Override
    public void renderTransform() { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
        containsMouse = getBounds().contains(e.getX(), e.getY());
    }
}
