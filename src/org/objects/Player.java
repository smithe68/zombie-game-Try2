package org.objects;

import org.engine.components.BoxCollider;
import org.engine.logic.*;
import org.engine.portation.*;
import org.engine.rendering.*;
import org.inventory.InventoryManager;
import org.inventory.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
    public double health = 100;

    private ActionBar actionBar;
    private ProgressBar healthBar;
    private BufferedImage equipped;
    private Item equippedItem;

    public Player(double x, double y)
    {
        super(x, y);
        image = SpriteLoader.getSprite("PlayerDude.png");

        // Create HUD
        healthBar = (ProgressBar)Level.instantiate(new ProgressBar(5, 5));
        healthBar.setColors(Color.white, Color.red, Color.white);
        healthBar.setValues(1, (int)health, 100);
        healthBar.setAttributes(30, 5);
        healthBar.setShowAmount(false);

        actionBar = (ActionBar)Level.instantiate(new ActionBar(0, 0, this));

        components.add(new BoxCollider(this));
    }

    @Override
    public void update()
    {
        Camera.x = x;
        Camera.y = y;

        velX = Input.horizontal;
        velY = Input.vertical;

        healthBar.setAmount((int)health);

        if(Input.getKeyDown(KeyEvent.VK_SPACE))
            if(getEquippedItem() != null)
                equippedItem.use();
    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform transform = g.getTransform();

        // Get Difference Between Mouse Position and Center of Screen
        double xDiff = (Input.mousePos.x / Renderer.getResolutionFactor().width) - Renderer.getResolution().width / 2;
        double yDiff = (Input.mousePos.y / Renderer.getResolutionFactor().height) - Renderer.getResolution().height / 2;

        // Calculate Rotation in Degrees from Player to Mouse Position
        rotation = Math.toDegrees(Math.atan2(yDiff - y + Camera.y, xDiff - x + Camera.x));

        // Draw the Player and Rotate it to Mouse
        g.rotate(Math.toRadians(rotation), posX + width / 2, posY + height / 2);
        g.drawImage(image, (int)posX, (int)posY, width, height, null);

        System.out.println("Rotation: " + rotation);

        // Draw Equipped Items8
        if(equippedItem != null & equipped != null)
        {
            g.drawImage(equipped, (int)posX + width / 2 - 2,
                    (int)posY - height / 2 + 3, width, height, null);
        }

        g.setTransform(transform);
    }

    /* Hurt the Player's Health */
    public void hurt(double amount)
    {
        if(health > 0)
            health -= amount;
    }

    /* Heal the Player's Health */
    public void heal(double amount)
    {
        if(health < 100)
            health += amount;
    }

    /* Sets the Equipped Item of the Player */
    public void setEquippedItem(Item item)
    {
        equippedItem = item;
        equipped = SpriteLoader.getSprite(equippedItem.image);
    }

    /* UnEquip Item */
    public void unEquipItem()
    {
        equippedItem = null;
        equipped = null;
    }

    /* Return the Equipped Item */
    public Item getEquippedItem() { return equippedItem; }
}
