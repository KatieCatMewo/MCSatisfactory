package net.kittykat.mcsatisfactory.components.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public abstract class BetterItemComponent extends ItemComponent implements AutoSyncedComponent {
    public BetterItemComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    /**
     * @see NbtCompound#getByte(String)
     */
    protected byte getByte(String key) {
        NbtCompound rootTag = getRootTag();
        return rootTag == null ? 0 : rootTag.getByte(key);
    }
    /**
     * @see NbtCompound#putByte(String, byte)
     */
    protected void putByte(String key, byte value) {
        if (value != 0) {
            getOrCreateRootTag().putByte(key, value);
        } else {
            remove(key);
        }
    }

    /**
     * @see NbtCompound#getShort(String)
     */
    protected short getShort(String key) {
        NbtCompound rootTag = getRootTag();
        return rootTag == null ? 0 : rootTag.getShort(key);
    }
    /**
     * @see NbtCompound#putShort(String, short)
     */
    protected void putShort(String key, short value) {
        if (value != 0) {
            getOrCreateRootTag().putShort(key, value);
        } else {
            remove(key);
        }
    }
}
