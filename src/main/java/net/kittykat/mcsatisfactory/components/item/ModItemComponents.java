package net.kittykat.mcsatisfactory.components.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.kittykat.mcsatisfactory.components.ModComponents;
import net.kittykat.mcsatisfactory.components.item.equipment.JetpackDataComponent;
import net.kittykat.mcsatisfactory.components.item.equipment.weapon.RebarGunDataComponent;
import net.kittykat.mcsatisfactory.components.item.equipment.weapon.RifleDataComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.item.ModItems.*;
import static net.kittykat.mcsatisfactory.components.ModComponents.*;

public class ModItemComponents implements ItemComponentInitializer {
    @Override
    public void registerItemComponentFactories(@NotNull ItemComponentFactoryRegistry registry) {
        ModComponents.register("item");
        register(registry, JETPACK,   JETPACK_DATA,   JetpackDataComponent::new);
        register(registry, REBAR_GUN, REBAR_GUN_DATA, RebarGunDataComponent::new);
        register(registry, RIFLE,     RIFLE_DATA,     RifleDataComponent::new);
    }

    @SuppressWarnings("UnstableApiUsage")
    private static <C extends ItemComponent> void register(@NotNull ItemComponentFactoryRegistry registry, Item item,
                                                           ComponentKey<C> key, ItemComponentFactory<C> factory) {
        registry.register(item, key, stack -> factory.make(stack, key));
    }

    @FunctionalInterface
    private interface ItemComponentFactory<C extends ItemComponent> {
        C make(ItemStack stack, ComponentKey<C> key);
    }
}
