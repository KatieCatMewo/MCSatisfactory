package net.kittykat.mcsatisfactory.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.kittykat.mcsatisfactory.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class JetpackDataComponent implements Component, AutoSyncedComponent {
    private static final String PREFERRED_FUEL_KEY = "preferred_fuel";
    private static final String REMAINING_FUEL_KEY = "remaining_fuel";
    private static final String USED_FUEL_KEY      = "used_fuel";
    private byte  preferredFuel = 0;
    public  short remainingFuel = 0;
    public  short usedFuel      = 0;

    private static final Item[]   FUEL_ITEMS = {
            Items.CHARCOAL,            ModItems.SOLID_BIOFUEL,        ModItems.PACKAGED_FUEL,         ModItems.PACKAGED_TURBOFUEL,
            ModItems.PACKAGED_BIOFUEL, ModItems.PACKAGED_ROCKET_FUEL, ModItems.PACKAGED_IONIZED_FUEL
    };
    private static final int[]    MAX_FUEL   = {40, 56,  100,  66,  250,  80,  140};
    private static final double[] VELOCITIES = {4d, 5d, 4.4d, 14d, 4.5d, 30d, 22.9d};
    public boolean isActive = false;

    // Server
    public boolean queueActivate = false;
    public boolean queueDeactivate = false;

    public void nextPreferredFuel() {
        preferredFuel = (byte) ((preferredFuel + 1) % FUEL_ITEMS.length);
    }

    public Item getPreferredFuel() {
        return FUEL_ITEMS[preferredFuel];
    }
    public float getMaxFuelTicks() {
        return MAX_FUEL[preferredFuel];
    }
    public double getTargetVelocity() {
        return VELOCITIES[preferredFuel] / 20;
    }

    public static boolean isFuel(Item item) {
        for (Item fuelItem : FUEL_ITEMS) {
            if (fuelItem.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void updateActive(boolean active) {
        isActive = active;
        if (active) {
            queueActivate   = true;
        } else {
            queueDeactivate = true;
        }
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound nbt) {
        preferredFuel = nbt.getByte(PREFERRED_FUEL_KEY);
        remainingFuel = nbt.getShort(REMAINING_FUEL_KEY);
        usedFuel      = nbt.getShort(USED_FUEL_KEY);
    }
    @Override
    public void writeToNbt(@NotNull NbtCompound nbt) {
        nbt.putByte(PREFERRED_FUEL_KEY,  preferredFuel);
        nbt.putShort(REMAINING_FUEL_KEY, remainingFuel);
        nbt.putShort(USED_FUEL_KEY,      usedFuel);
    }
}
