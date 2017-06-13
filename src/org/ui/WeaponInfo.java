package org.ui;

public class WeaponInfo
{
    public int damage;
    public int range;
    public AttackType type;
    public AmmoType ammo;

    public enum AttackType
    {
        Shoot,
        Melee,
        Explosion
    }

    public enum AmmoType
    {
        None,
        PistolAmmo
    }

    public WeaponInfo(int dam, int ran, AttackType attack, AmmoType ammo)
    {
        damage = dam;
        range = ran;
        type = attack;
        this.ammo = ammo;
    }
}
