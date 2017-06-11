package org.objects;

import org.engine.ID;

import java.awt.*;

public abstract class GameObject
{
    protected float x, y;
    protected ID id;
    protected float velX, velY;

    public GameObject(float x, float y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(float deltaTime);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void SetX(float x)
    {
        this.x = x;
    }

    public void SetY(float y)
    {
        this.y = y;
    }

    public float GetX()
    {
        return x;
    }

    public float GetY()
    {
        return y;
    }

    public void SetID(ID id)
    {
        this.id = id;
    }

    public ID GetID()
    {
        return id;
    }

    public void SetVelX(float velX)
    {
        this.velX = velX;
    }

    public void SetVelY(float velY)
    {
        this.velY = velY;
    }

    public float GetVelX()
    {
        return velX;
    }

    public float GetVelY()
    {
        return velY;
    }
}
