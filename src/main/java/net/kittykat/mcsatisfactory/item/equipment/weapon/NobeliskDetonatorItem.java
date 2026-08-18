package net.kittykat.mcsatisfactory.item.equipment.weapon;

import net.minecraft.item.ItemStack;

public class NobeliskDetonatorItem extends RangedWeaponItem {
    public NobeliskDetonatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(ItemStack stack) {
        return 2;
    }
}
