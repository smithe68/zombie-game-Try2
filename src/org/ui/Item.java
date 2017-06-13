package org.ui;

public class Item
{
    public String itemName;
    public String itemDesc;
    public int itemID;
    public int stackSize;
    public ItemType itemType;
    public ItemEffect itemEffect;
    public float effectAmount;

    public Item(String s, String s1, int i, int i1, ItemType misc, ItemEffect nothing, float v) {
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
