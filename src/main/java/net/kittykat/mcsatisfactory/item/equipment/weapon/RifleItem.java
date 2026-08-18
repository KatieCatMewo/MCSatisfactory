package net.kittykat.mcsatisfactory.item.equipment.weapon;

import net.kittykat.mcsatisfactory.components.ModComponents;
import net.minecraft.item.ItemStack;

public class RifleItem extends RangedWeaponItem {
    private static final int BLOOM_SCALE     = 3;
    private static final int MAX_BLOOM_STEPS = 15;
    private static final int MAX_BLOOM       = BLOOM_SCALE * MAX_BLOOM_STEPS;

    public RifleItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getCrosshairIndex(ItemStack stack) {
        return ModComponents.RIFLE_DATA.get(stack).getAmmoType();
    }
    public int getCrosshairBloom(int i) {
        return switch (i) {
            case 0  -> 0;  // normal
            case 1  -> 0;  // turbo
            case 2  -> MAX_BLOOM;  // homing
            default -> 0;
        };
    }
}
