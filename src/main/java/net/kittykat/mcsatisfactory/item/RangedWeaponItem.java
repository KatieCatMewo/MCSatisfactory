package net.kittykat.mcsatisfactory.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.NotNull;

public abstract class RangedWeaponItem extends Item {
    public RangedWeaponItem(@NotNull Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.COMMON));
    }

    public int getCrosshairIndex(PlayerEntity player) {
        return 0;
    }
}
