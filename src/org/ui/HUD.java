package org.ui;

import org.objects.GameObject;
import org.engine.ID;
import org.engine.Renderer;
import org.objects.Player;

import java.awt.*;

public class HUD extends GameObject
{
    private GameObject player = Player.player;

    public HUD (float x, float y, ID id)
    {
        super(x, y, id);
    }

    public static float HEALTH = 100;

    private int greenValue = 255;

    public void tick(float deltaTime)
    {
        HEALTH = Renderer.Clamp(HEALTH,0,100);

        // Change Color of Health
        greenValue = (int) Renderer.Clamp(greenValue, 0, 255);
        greenValue = (int)HEALTH * 2;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int)x,(int)y,50,12);
        g.setColor(new Color(150, greenValue, 0));
        g.fillRect((int)x,(int)y,(int)HEALTH/2 ,12);
        g.setColor(Color.white);
        g.drawRect((int)x,(int)y,50,12);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
