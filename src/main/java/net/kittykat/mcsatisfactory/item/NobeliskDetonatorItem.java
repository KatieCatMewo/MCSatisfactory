package net.kittykat.mcsatisfactory.item;

import net.minecraft.entity.player.PlayerEntity;

public class NobeliskDetonatorItem extends RangedWeaponItem {
    public NobeliskDetonatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(PlayerEntity player) {
        return 2;
    }
}
