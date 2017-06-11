package org.ui;

import org.objects.GameObject;
import org.engine.ID;
import org.engine.Renderer;
import org.world.World;

import java.awt.*;

public class HUD
{

    public HUD (){
    }

    public static float HEALTH = 100;

    private int healthPosX;
    private int healthPosY;

    private int greenValue = 255;

    public void tick()
    {
        HEALTH = Renderer.Clamp(HEALTH,0,100);

        // Change Color of Health
        greenValue = (int) Renderer.Clamp(greenValue, 0, 255);
        greenValue = (int)HEALTH * 2;

        for(int i = 0; i < World.gameObjects.size(); i++)
        {
            GameObject tempObject = World.gameObjects.get(i);

            if(tempObject.GetID() == ID.Player)
            {
                healthPosX = (int)tempObject.GetX() + 10;
                healthPosY = (int)tempObject.GetY() - 20;
            }
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(healthPosX,healthPosY,50,12);
        g.setColor(new Color(150, greenValue, 0));
        g.fillRect(healthPosX,healthPosY,(int)HEALTH/2 ,12);
        g.setColor(Color.white);
        g.drawRect(healthPosX,healthPosY,50,12);
    }

}
