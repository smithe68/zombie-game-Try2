package org.objects;

import org.engine.logic.Input;
import org.engine.logic.UIObject;
import org.engine.rendering.Renderer;
import org.inventory.InventoryManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ActionBar extends UIObject
{
    private Player player;

    public ActionBar(double x, double y, Player player)
    {
        super(x, y);
        this.player = player;
    }

    @Override
    public void update()
    {
        if(Input.getKeyDown(KeyEvent.VK_1))
            if(InventoryManager.indicesOK(0)) {
                InventoryManager.equipItem(0);
            } else { player.unEquipItem(); }

        if(Input.getKeyDown(KeyEvent.VK_2))
            if(InventoryManager.indicesOK(1)) {
                InventoryManager.equipItem(1);
            } else { player.unEquipItem(); }

        if(Input.getKeyDown(KeyEvent.VK_3))
            if(InventoryManager.indicesOK(2)) {
                InventoryManager.equipItem(2);
            } else { player.unEquipItem(); }

        if(Input.getKeyDown(KeyEvent.VK_4))
            if(InventoryManager.indicesOK(3)) {
                InventoryManager.equipItem(3);
            } else { player.unEquipItem(); }
    }

    @Override
    public void render(Graphics2D g)
    {
        int barY = Renderer.getResolution().height - 25;

        // Draw Slots
        g.setColor(Color.white);
        for(int i = 0; i < InventoryManager.MAX_SIZE; i++)
            g.drawRect((i * 16) + 5, barY, width, height);

        // Draw Items
        for(int i = 0; i < InventoryManager.itemImages.size(); i++)
        {
            BufferedImage currImage = InventoryManager.itemImages.get(i);

            int centerY = barY - height / 2;
            int centerX = (i * 16 + 5) - width / 2;
            int imageHeight = currImage.getHeight();
            int imageWidth = currImage.getWidth();

            g.drawImage(currImage, centerX, centerY, imageWidth, imageHeight, null);
        }
    }
}
