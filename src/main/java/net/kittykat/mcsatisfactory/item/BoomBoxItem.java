package net.kittykat.mcsatisfactory.item;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.NotNull;

public class BoomBoxItem extends Item {
    public BoomBoxItem(@NotNull Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.UNCOMMON));
    }
}
