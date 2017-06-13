package org.ui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.enums.ID;
import org.input.Input;
import org.objects.GameObject;
import org.objects.Player;

import java.awt.*;

public class ActionBar extends GameObject
{
    public Inventory inv = Player.inv;

    public ActionBar(int x, int y, ID id)
    {
        super(x, y, id);
    }

    public void tick(float deltaTime)
    {

    }

    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.red);

        g2.drawRect(20, 220, 20, 20);
        g2.drawImage(inv.items.get(0).itemSprite, 14, 215, null);

        g2.drawRect(50, 220, 20, 20);
        g2.drawImage(inv.items.get(1).itemSprite, 44, 215, null);

        g2.drawRect(80, 220, 20, 20);
        g2.drawImage(inv.items.get(2).itemSprite, 74, 215, null);

        g2.drawRect(110, 220, 20, 20);
        g2.drawImage(inv.items.get(3).itemSprite, 104, 215, null);

        g2.drawRect(140, 220, 20, 20);
        g2.drawImage(inv.items.get(4).itemSprite, 134, 215, null);
    }

    public Rectangle getBounds()
    {
        return null;
    }
}
