package org.ui;

public class WeaponDatabase
{
    public WeaponInfo empty;
    public WeaponInfo pistol;

    public void CreateWeaponData()
    {
        empty = new WeaponInfo
        (
            10,
            10,
            WeaponInfo.AttackType.Melee,
            WeaponInfo.AmmoType.None
        );

        pistol = new WeaponInfo
        (
            10,
            100,
            WeaponInfo.AttackType.Shoot,
            WeaponInfo.AmmoType.PistolAmmo
        );
    }
}
