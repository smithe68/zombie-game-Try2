package org.objects;

import org.engine.components.*;
import org.engine.logic.*;
import org.engine.portation.*;
import org.engine.rendering.*;
import org.inventory.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
    public double health = 100;

    private ProgressBar healthBar;
    private BufferedImage equipped;
    private Item equippedItem;

    private Rigidbody rb;

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

        Level.instantiate(new ActionBar(0, 0, this));

        addComponent(new BoxCollider(this));
        rb = addComponent(new Rigidbody(this));
    }

    @Override
    public void update()
    {
        Camera.setPosition(x, y);
        rb.setVelocity(Input.horizontal, Input.vertical);
        healthBar.setAmount((int)health);

        if(Input.getKeyDown(KeyEvent.VK_SPACE))
            if(getEquippedItem() != null)
                equippedItem.use();
    }

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform transform = g.getTransform();

        // Get Rotation to Mouse Position
        rotation = Mathf.lookAtMouse(x, y);

        // Draw the Player and Rotate it to Mouse
        g.rotate(rotation, posX + width / 2, posY + height / 2);
        g.drawImage(image, (int)posX, (int)posY, width, height, null);

        // Draw Equipped Items
        if(equippedItem != null & equipped != null)
        {
            int equipX = (int)posX + width / 2 - 2;
            int equipY = (int)posY - height / 2 + 3;

            g.drawImage(equipped, equipX, equipY, width, height, null);
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
