package net.kittykat.mcsatisfactory.item;

import net.kittykat.mcsatisfactory.components.ModComponents;
import net.minecraft.entity.player.PlayerEntity;

public class RebarGunItem extends RangedWeaponItem {
    public RebarGunItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(PlayerEntity player) {
        return ModComponents.PREFERRED_AMMO.get(player).rebarAmmo;
    }
}
