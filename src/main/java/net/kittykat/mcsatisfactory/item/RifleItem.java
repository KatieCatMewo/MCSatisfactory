package net.kittykat.mcsatisfactory.item;

import net.kittykat.mcsatisfactory.components.ModComponents;
import net.minecraft.entity.player.PlayerEntity;

public class RifleItem extends RangedWeaponItem {
    public RifleItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(PlayerEntity player) {
        return 4 + ModComponents.PREFERRED_AMMO.get(player).rifleAmmo;
    }
}
