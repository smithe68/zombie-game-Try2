package org.engine;

import org.enums.ID;
import org.enums.PickupTypes;
import org.objects.Pickup;

public class Spawn
{
    public static void SpawnPickup(int x, int y, PickupTypes type, int amount, boolean rotate)
    {
        Game.Instantiate(new Pickup(x, y, ID.Pickup, type, amount,rotate));
    }
}
