package org.objects;

import org.engine.*;
import org.enums.ID;
import org.input.Input;
import org.ui.*;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject
{
    public float speed = 1.5f;

    public static Player player;
    public static Inventory inv;
    public static Database data;

    public BufferedImage image;
    public BufferedImage equip;

    public ActionBar action;

    private GameObject hudObj;
    private HUD hud;

    private Point mouse = new Point(0, 0);

    private double angle;
    private double xDiff;
    private double yDiff;

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

        // Create the HUD
        hud = new HUD(x, y + 15, ID.HUD, 25, 8);
        hudObj = Game.Instantiate(hud);

        // Create Item Database
        data = new Database();
        data.CreateItems();

        // Create the Inventory
        inv = new Inventory();
        inv.Initialize();

        // Create the Action Bar
        action = new ActionBar(0, 0, ID.HUD);
        Game.Instantiate(action);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,45,45);
    }

    // This Updates Every Frame
    public void tick(float deltaTime)
    {
        // Draw HUD above Player
        if(hudObj != null)
        {
            // Make Health float above Player
            hudObj.x = player.x;
            hudObj.y = player.y - 16;
        }

        // Lock player to Window Bounds
        x = CustomMathf.Clamp(x,0, Renderer.gameWidth +761);
        y = CustomMathf.Clamp(y,0, Renderer.gameHeight +531);

        // Move Player Left
        if(Input.GetKey(KeyEvent.VK_A)) { x -= speed; }

        // Move Player Right
        if(Input.GetKey(KeyEvent.VK_D)) { x += speed; }

        // Move Player Up
        if(Input.GetKey(KeyEvent.VK_W)) { y -= speed; }

        // Move Player Down
        if(Input.GetKey(KeyEvent.VK_S)) { y += speed; }

        // Use
        if(Input.GetKeyDown(KeyEvent.VK_F)) { Use(action.selectedItem); }

        Collision();
    }

    private void Collision()
    {
        // Check for Collision with Zombies
        // TODO Change how this works
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

        // Quit Game when Health is Zero
        if(hud.HEALTH == 0)
        {
            System.exit(1);
        }
    }

    public void Use(Item useItem)
    {
        if(useItem.weaponInfo.type == WeaponInfo.AttackType.Shoot)
        {
            if(useItem.weaponInfo.ammo == WeaponInfo.AmmoType.PistolAmmo)
            {
                Game.Instantiate(new Bullet((int)(x + 10), (int)(y - 10),
                        ID.Speeder, useItem.weaponInfo, xDiff, yDiff, double angle));
            }
        }

        if(useItem.weaponInfo.type == WeaponInfo.AttackType.Melee)
        {
            System.out.println("test");
        }
    }

    public void render(Graphics g)
    {
        // Set the Transform for the Player
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        AffineTransform transform = g2.getTransform();

        // Get Mouse Position
        mouse = MouseInfo.getPointerInfo().getLocation();
        mouse.x /= 4.8;
        mouse.y /= 4.4;

        int centerX = (int)x + image.getWidth() / 2;
        int centerY = (int)y + image.getHeight() / 2;

        xDiff = mouse.x - Math.abs(centerX);
        yDiff = mouse.y - Math.abs(centerY);

        // Get Rotation to Mouse
        angle = Math.toDegrees(Math.atan2(yDiff, xDiff));

        // Rotate the Player to Mouse Cursor
        g2.rotate(Math.toRadians(angle), centerX, centerY);

        // Draw the Player Sprite
        g2.drawImage(image, at, null);

        // Draw the Equipped Item
        g2.drawImage(equip, (int)(x + 10), (int)(y - 10), null);

        // Set Position of Player
        g2.setTransform(transform);
    }
}
