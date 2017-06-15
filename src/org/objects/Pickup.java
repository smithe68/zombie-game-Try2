package org.objects;

import org.engine.CustomMath;
import org.engine.GameObject;
import org.engine.Renderer;
import org.engine.Sound;
import org.enums.ID;
import org.enums.PickupTypes;
import org.ui.Database;
import org.ui.Item;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pickup extends GameObject
{
    private Player player = Player.player;

    private Item currentItem;
    private int currentAmount = 1;
    private String pickupSoundPath;
    private BufferedImage currentImage;

    private BufferedImage pistol;
    private BufferedImage pack;

    public PickupTypes type;

    public Database data = Player.data;

    private float rotation = 0;
    private float scale = 1;
    private float setScale = 1;

    private boolean canRotate = false;

    private float centerX = 0;
    private float centerY = 0;

    public Pickup(int x, int y, ID id, PickupTypes type, int amount, boolean rotate)
    {
        super(x, y, id);

        this.type = type;
        currentAmount = amount;
        canRotate = rotate;

        try
        {
            pistol = Renderer.LoadImage("/resources/sprites/Pistol_Side.png");
            pack = Renderer.LoadImage("/resources/sprites/HealthPack.png");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        data.CreateItems();
        ChooseType();
    }

    public void Update()
    {
        rotation += 2 * Renderer.deltaTime;

        if(scale >= 1.4f) setScale = 1f;
        if(scale <= 1.1) setScale = 1.5f;

        scale = CustomMath.Lerp(scale, setScale, Renderer.deltaTime);

        Collision();
    }

    public void Render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        centerX = posX - currentImage.getWidth() / 2;
        centerY = posY - currentImage.getHeight() / 2;

        int posX = (int)(centerX - Renderer.camPosX);
        int posY = (int)(centerY - Renderer.camPosY);

        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);

        at.scale(scale * 1.1, scale * 1.1);

        if(canRotate)
        {
            at.rotate(rotation, currentImage.getWidth() / 2,
                    currentImage.getHeight() / 2);
        }

        // Draw the Player's Sprite
        g2d.drawImage(currentImage, at, null);
    }

    public Rectangle getBounds()
    {
        AffineTransform transform = new AffineTransform();
        Rectangle rect = new Rectangle((int)(centerX - Renderer.camPosX),
                (int)(centerY - Renderer.camPosY), currentImage.getWidth(), currentImage.getHeight());
        transform.rotate(rotation, rect.width / 2, rect.height / 2);
        return rect;
    }

    public void ChooseType()
    {
        switch (type)
        {
            case Pistol:
                currentImage = pistol;
                currentItem = data.pistol;
                pickupSoundPath = "/resources/sounds/Pickup_04.wav";
                break;

            case HealthPack:
                currentImage = pack;
                currentItem = data.pack;
                pickupSoundPath = "/resources/sounds/Pickup_04.wav";
                break;
        }
    }

    public void Collision()
    {
        if( getBounds().intersects(player.getBounds()))
        {
            CollectSound();
            player.inv.AddToInventory(currentItem, currentAmount, this);
        }
    }

    public void CollectSound()
    {
        // Play Sound if Collected
        Sound.PlaySound(pickupSoundPath, -20.0f, false);
    }
}

