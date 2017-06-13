package org.objects;

import org.engine.CustomMathf;
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

    public Pickup(int x, int y, ID id, PickupTypes type, int amount)
    {
        super(x, y, id);

        this.type = type;
        currentAmount = amount;

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

    public void tick(float deltaTime)
    {
        rotation += 2 * deltaTime;

        if(scale >= 1.4f) setScale = 1f;
        if(scale <= 1.1) setScale = 1.5f;
        scale = CustomMathf.Lerp(scale, setScale, deltaTime);

        collision();
    }

    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale * 1.1, scale * 1.1);

        at.rotate(rotation, currentImage.getWidth() / 2,
                currentImage.getHeight() / 2);

        // Draw the Player's Sprite
        g2d.drawImage(currentImage, at, null);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,currentImage.getWidth(),currentImage.getHeight());
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
                pickupSoundPath = "/resources/sounds/Pickup_03.wav";
        }
    }

    public void collision()
    {
        if( getBounds().intersects(player.getBounds()))
        {
            CollectSound();
            player.inv.AddToInventory(currentItem, currentAmount, this);
        }
    }

    public void CollectSound()
    {
        Sound.PlaySound(pickupSoundPath, -20.0f, false);
    }
}

