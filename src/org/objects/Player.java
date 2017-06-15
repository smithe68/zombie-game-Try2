package org.objects;

import org.engine.*;
import org.enums.*;
import org.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject
{
    public float speed = 60;

    public static Player player;
    public static Inventory inv;
    public static Database data;

    public BufferedImage equip;

    public ActionBar action;

    private Item useItem;
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
            equip = Renderer.LoadImage("/resources/sprites/Blank.png");
        }
        catch (IOException e) {e.printStackTrace();}

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
        AffineTransform transform = new AffineTransform();
        Rectangle rect = new Rectangle((int) posX,
                (int) posY - image.getHeight(), image.getWidth(), image.getHeight());
        transform.rotate(Math.toRadians(angle), rect.width / 2, rect.height / 2);
        return rect;
    }

    // This Updates Every Frame
    public void Update()
    {
        // Draw HUD above Player
        if(hudObj != null)
        {
            // Make Health float above Player
            hudObj.posX = player.posX;
            hudObj.posY = player.posY - 16;
        }

        // Lock player to Window Bounds
        posX = CustomMath.Clamp(posX,0, Renderer.gameWidth +761);
        posY = CustomMath.Clamp(posY,0, Renderer.gameHeight +531);

        float horiAxis = Input.Horizontal;
        float vertAxis = Input.Vertical;

        posX += horiAxis * speed * Renderer.deltaTime;
        posY += vertAxis * speed * Renderer.deltaTime;

        // Use Item
        if(Input.GetKeyDown(KeyEvent.VK_F))
        {
            if(action.selectedItem != null)
            {
                Use(action.selectedItem, action.selectedItem.effectAmount);
            }
        }

        // Drop Item
        if(Input.GetKeyDown(KeyEvent.VK_G))
        {
            Drop(action.selectedIndex, action.selectedAmount, true);
        }

        Collision();

        Renderer.camX = posX;
        Renderer.camY = posY;
    }

    private void Collision()
    {
        // Check for Collision with Zombies
        // TODO Change how this works
        for(int i = 0; i < Level.objects.size(); i++)
        {
            GameObject tempObject = Level.objects.get(i);

            if (tempObject.id == ID.BasicZombie)
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

    public void Use(Item item, int amount)
    {
        if(item == null) return;

        useItem = item;

        if(useItem.weaponInfo.type == WeaponInfo.AttackType.Shoot)
        {
            if(useItem.weaponInfo.ammo == WeaponInfo.AmmoType.PistolAmmo)
            {
                Sound.PlaySound("/resources/sounds/Shoot_01.wav", -20, false);
                Game.Instantiate(new Bullet((int)(posX + 10), (int)(posY - 10),
                        ID.Bullet, useItem.weaponInfo, xDiff, yDiff, angle));
            }
        }
        // TODO set up melee system
        if(useItem.weaponInfo.type == WeaponInfo.AttackType.Melee)
        {
            System.out.println("test");
        }

        if(useItem.itemEffect == Item.ItemEffect.Heal)
        {
            hud.HEALTH += amount;
            Drop(action.selectedIndex, action.selectedAmount, false);
        }
    }

    public void Drop(int itemIndex, int amount, boolean drop)
    {
        if(inv.items.get(itemIndex).pickup == PickupTypes.Nothing) return;

        System.out.println(inv.items.get(itemIndex).itemName);

        if(drop)
        {
            Game.Instantiate(new Pickup((int)(posX + 64), (int) posY, ID.Pickup,
                    inv.items.get(itemIndex).pickup, amount,
                    inv.items.get(itemIndex).canRotate));
        }

        inv.RemoveItem(itemIndex);
        action.ItemAction(itemIndex, inv.items.get(itemIndex).itemEffect);

        useItem = null;
        action.selectedItem = null;
    }

    public void Render(Graphics g)
    {
        // Set the Transform for the Player
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
        AffineTransform transform = g2.getTransform();

        // Get Mouse Position
        mouse = MouseInfo.getPointerInfo().getLocation();
        mouse.x /= 4.8;
        mouse.y /= 4.4;

        int centerX = (int) posX + image.getWidth() / 2;
        int centerY = (int) posY + image.getHeight() / 2;

        xDiff = mouse.x - Math.abs(centerX);
        yDiff = mouse.y - Math.abs(centerY);

        // Get Rotation to Mouse
        // angle is publicly stored in degrees
        angle = Math.toDegrees(Math.atan2(yDiff, xDiff));

        // Rotate the Player to Mouse Cursor
        g2.rotate(Math.toRadians(angle), centerX, centerY);

        // Draw the Player Sprite
        g2.drawImage(image, at, null);

        // Draw the Equipped Item
        g2.drawImage(equip, (int)(posX + 10), (int)(posY - 10), null);

        // Set Position of Player
        g2.setTransform(transform);
    }
}
