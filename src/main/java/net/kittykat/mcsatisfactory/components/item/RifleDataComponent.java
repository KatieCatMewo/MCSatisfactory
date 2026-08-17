package net.kittykat.mcsatisfactory.components.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.kittykat.mcsatisfactory.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RifleDataComponent extends WeaponDataComponent {
    public static final Item[] AMMO_ITEMS = {
            ModItems.RIFLE_AMMO, ModItems.RIFLE_AMMO_TURBO, ModItems.RIFLE_AMMO_HOMING
    };

    public RifleDataComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    @Override
    public Item[] getAmmoItems() {
        return AMMO_ITEMS;
    }
}
