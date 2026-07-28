package net.kittykat.mcsatisfactory.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.kittykat.mcsatisfactory.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class PreferredAmmoComponent implements Component, AutoSyncedComponent {
    private static final String REBAR_AMMO_KEY = "rebar_ammo";
    private static final String RIFLE_AMMO_KEY = "rifle_ammo";
    public byte rebarAmmo = 0;
    public byte rifleAmmo = 0;

    private static final Item[] REBAR_AMMO_ITEMS = {
            ModItems.REBAR,         ModItems.REBAR_EXPLOSIVE, ModItems.REBAR_SHATTER, ModItems.REBAR_STUN
    };
    private static final Item[] RIFLE_AMMO_ITEMS = {
            ModItems.RIFLE_AMMO, ModItems.RIFLE_AMMO_TURBO, ModItems.RIFLE_AMMO_HOMING
    };

    public Item getRebarAmmo() {
        return REBAR_AMMO_ITEMS[rebarAmmo];
    }
    public Item getRifleAmmo() {
        return RIFLE_AMMO_ITEMS[rifleAmmo];
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound nbt) {
        rebarAmmo = nbt.getByte(REBAR_AMMO_KEY);
        rifleAmmo = nbt.getByte(RIFLE_AMMO_KEY);
    }
    @Override
    public void writeToNbt(@NotNull NbtCompound nbt) {
        nbt.putByte(REBAR_AMMO_KEY, rebarAmmo);
        nbt.putByte(RIFLE_AMMO_KEY, rifleAmmo);
    }
}
