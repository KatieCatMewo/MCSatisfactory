package net.kittykat.mcsatisfactory.components.item.equipment.weapon;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.kittykat.mcsatisfactory.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RebarGunDataComponent extends WeaponDataComponent {
    public static final Item[] AMMO_ITEMS = {
            ModItems.REBAR, ModItems.REBAR_EXPLOSIVE, ModItems.REBAR_SHATTER, ModItems.REBAR_STUN
    };

    public RebarGunDataComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    @Override
    public Item[] getAmmoItems() {
        return AMMO_ITEMS;
    }
}
