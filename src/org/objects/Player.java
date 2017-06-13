package org.objects;

import org.engine.*;
import org.enums.ID;
import org.input.Input;
import org.ui.ActionBar;
import org.ui.Database;
import org.ui.HUD;
import org.ui.Inventory;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject
{
    public static Player player;

    public BufferedImage image;
    public BufferedImage equip;

    private GameObject hudObj;
    private HUD hud;

    public ActionBar action;

    public float speed = 1.5f;

    private double rotation = 0;

    private float horiAxis = 0;
    private float vertAxis = 0;

    private Point mouse = new Point(0, 0);

    public static Inventory inv;
    public static Database data;

    public Player(int x, int y, ID id)
    {
        super(x, y, id);

        player = this;

        try
        {
            // Get the Sprite for the Player
            image = Renderer.LoadImage("/resources/sprites/PlayerDude.png");
            equip = Renderer.LoadImage("/resources/sprites/SmallShadow.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        hud = new HUD(x, y + 15, ID.HUD, 25, 8);
        hudObj = Game.Instantiate(hud);

        data = new Database();
        data.CreateItems();

        inv = new Inventory();
        inv.Initialize();

        action = new ActionBar(0, 0, ID.HUD);
        Game.Instantiate(action);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,45,45);
    }

    public void tick(float deltaTime)
    {
        // UI

        if(hudObj != null)
        {
            // Make Health float above Player
            hudObj.x = player.x;
            hudObj.y = player.y - 16;
        }

        // Movement

        x+= velX;
        y += velY;

        x = CustomMathf.Clamp(x,0, Renderer.gameWidth +761);
        y = CustomMathf.Clamp(y,0, Renderer.gameHeight +531);

        horiAxis = 0;
        vertAxis = 0;

        // Move Player Left
        if(Input.GetKey(KeyEvent.VK_A))
        {
            x -= speed;
            horiAxis = -1;
        }

        // Move Player Right
        if(Input.GetKey(KeyEvent.VK_D))
        {
            x += speed;
            horiAxis = 1;
        }

        // Move Player Up
        if(Input.GetKey(KeyEvent.VK_W))
        {
            y -= speed;
            vertAxis = 1;
        }

        // Move Player Down
        if(Input.GetKey(KeyEvent.VK_S))
        {
            y += speed;
            vertAxis = -1;
        }

        collision();
    }

    private void collision()
    {
        for(int i = 0; i < World.gameObjects.size(); i++)
        {
            GameObject tempObject = World.gameObjects.get(i);

            if (tempObject.GetID() == ID.BasicZombie)
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    hud.HEALTH -= 0.5f;
                }
            }
        }

        if(hud.HEALTH == 0)
        {
            System.exit(1);
        }
    }

    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        AffineTransform transform = g2.getTransform();

        mouse = MouseInfo.getPointerInfo().getLocation();
        mouse.x /= 4.8;
        mouse.y /= 4.4;

        int centerX = (int)x + image.getWidth() / 2;
        int centerY = (int)y + image.getHeight() / 2;

        double xDiff = mouse.x - Math.abs(centerX);
        double yDiff = mouse.y - Math.abs(centerY);

        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));

        g2.rotate(Math.toRadians(angle), centerX, centerY);
        g2.drawImage(image, at, null);
        g2.drawImage(equip, (int)(x + 10), (int)(y - 10), null);
        g2.setTransform(transform);
    }
}
