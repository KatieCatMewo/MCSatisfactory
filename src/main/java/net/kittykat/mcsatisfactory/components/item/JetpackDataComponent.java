package net.kittykat.mcsatisfactory.components.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.item.ItemStack;

public class JetpackDataComponent extends BetterItemComponent {
    private static final String REMAINING_FUEL_KEY = "remaining_fuel";
    private static final String USED_FUEL_KEY      = "used_fuel";

    public boolean isActive = false;

    // Server
    public boolean queueActivate = false;
    public boolean queueDeactivate = false;

    public JetpackDataComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    public short getRemainingFuel() {
        return getShort(REMAINING_FUEL_KEY);
    }
    public void getRemainingFuel(short value) {
        putShort(REMAINING_FUEL_KEY, value);
    }

    public short getUsedFuel() {
        return getShort(USED_FUEL_KEY);
    }
    public void getUsedFuel(short value) {
        putShort(USED_FUEL_KEY, value);
    }

    public void updateActive(boolean active) {
        isActive = active;
        if (active) {
            queueActivate   = true;
        } else {
            queueDeactivate = true;
        }
    }
}
