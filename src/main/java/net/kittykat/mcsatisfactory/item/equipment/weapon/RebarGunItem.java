package net.kittykat.mcsatisfactory.item.equipment.weapon;

import net.kittykat.mcsatisfactory.components.ModComponents;
import net.minecraft.item.ItemStack;

public class RebarGunItem extends RangedWeaponItem {
    public RebarGunItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(ItemStack stack) {
        return ModComponents.REBAR_GUN_DATA.get(stack).getAmmoType();
    }
}
