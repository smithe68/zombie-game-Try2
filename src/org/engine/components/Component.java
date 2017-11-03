package org.engine.components;

import org.engine.logic.GameObject;

import java.awt.*;

public interface Component
{
    void update();

    default void render(Graphics2D g) {

    }
}
