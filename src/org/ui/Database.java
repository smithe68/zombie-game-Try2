package org.ui;

public class Database
{
    public static Item empty = new Item
    (
        "Empty",
        "Nothing",
        0,
        0,
        Item.ItemType.Misc,
        Item.ItemEffect.Nothing,
        0.0f
    );

    public static Item pistol = new Item
    (
            "Pistol",
            "A Small Weapon",
            1,
            1,
            Item.ItemType.Weapon,
            Item.ItemEffect.Nothing,
            0.0f
    );
}
