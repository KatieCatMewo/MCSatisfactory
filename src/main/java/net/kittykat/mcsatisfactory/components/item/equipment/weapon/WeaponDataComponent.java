package net.kittykat.mcsatisfactory.components.item.equipment.weapon;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.kittykat.mcsatisfactory.components.item.BetterItemComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class WeaponDataComponent extends BetterItemComponent {
    private static final String AMMO_TYPE_KEY = "ammo_type";
    private static final String AMMO_COUNT    = "ammo_count";

    public WeaponDataComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    public byte getAmmoType() {
        return getByte(AMMO_TYPE_KEY);
    }
    public void setAmmoType(byte value) {
        putByte(AMMO_TYPE_KEY, value);
    }
    public void nextAmmoType() {
        setAmmoType((byte) ((getAmmoType() + 1) % getAmmoTypeCount()));
    }

    public short getAmmoCount() {
        return getShort(AMMO_COUNT);
    }
    public void setAmmoCount(short value) {
        putShort(AMMO_COUNT, value);
    }
    public void decAmmoCount() {
        short count = getAmmoCount();
        if (count <= 0) return;

        setAmmoCount((short) (count - 1));
    }

    public abstract Item[] getAmmoItems();
    public Item getAmmoItem() {
        return getAmmoItems()[getAmmoType()];
    }
    public int getAmmoTypeCount() {
        return getAmmoItems().length;
    }
}
