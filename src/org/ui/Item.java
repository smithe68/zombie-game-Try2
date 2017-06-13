package org.ui;

import java.awt.image.BufferedImage;

public class Item
{
    public String itemName;
    public String itemDesc;
    public int itemID;
    public int stackSize;
    public BufferedImage itemSprite;
    public ItemType itemType;
    public ItemEffect itemEffect;
    public float effectAmount;

    public Item(String s, String s1, int i, int i1, BufferedImage sP, ItemType misc, ItemEffect nothing, float v)
    {
        itemName = s;
        itemDesc = s1;
        itemID = i;
        stackSize = i1;
        itemSprite = sP;
        itemType = misc;
        itemEffect = nothing;
        effectAmount = v;
    }

    public enum ItemType
    {
        Resource,
        Currency,
        Weapon,
        Armor,
        Special,
        Misc
    }

    public enum ItemEffect
    {
        Nothing,
        Heal,
        Hurt,
        Equip
    }
}