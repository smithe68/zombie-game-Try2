package org.objects;

import org.enums.ID;

import java.awt.*;

public abstract class GameObject
{
    public float x, y;
    public ID id;
    public float velX, velY;

    public GameObject(float x, float y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(float deltaTime);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public ID GetID()
    {
        return id;
    }
}
