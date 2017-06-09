import java.awt.*;

public abstract class GameObject
{
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public GameObject(int x, int y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void SetX(int x)
    {
        this.x = x;
    }

    public void SetY(int y)
    {
        this.y = y;
    }

    public int GetX()
    {
        return x;
    }

    public int GetY()
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

    public void SetVelX(int velX)
    {
        this.velX = velX;
    }

    public void SetVelY(int velY)
    {
        this.velY = velY;
    }

    public int GetVelX()
    {
        return velX;
    }

    public int GetVelY()
    {
        return velY;
    }
}
