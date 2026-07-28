package net.kittykat.mcsatisfactory.item;

import net.kittykat.mcsatisfactory.components.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class RebarGunItem extends RangedWeaponItem {
    public RebarGunItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(PlayerEntity player) {
        return 8 + ModComponents.PREFERRED_AMMO.get(player).rebarAmmo;
    }
}
