package org.inventory;

import org.engine.logic.GameObject;

public class Item
{
    public String name;
    public String desc;
    public int id;

    public String image;

    public GameObject parent;

    public Item(GameObject parent) {
        this.parent = parent;
    }

    public void equip() {
        System.out.println("Equipped " + name);
    }

    public void use() {
        System.out.println("Used " + name);
    }
}
