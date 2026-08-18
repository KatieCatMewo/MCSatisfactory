package net.kittykat.mcsatisfactory.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroup.EntryCollector;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.item.ModItems.*;

public abstract class ModItemGroups {
    public static final ItemGroup EQUIPMENT = registerItemGroup("equipment", COFFEE_CUP,
            ((displayContext, entries) -> {
                entries.add(COFFEE_CUP);
                entries.add(GOLDEN_COFFEE_CUP);

                entries.add(XENO_ZAPPER);
                entries.add(NOBELISK_DETONATOR);
                entries.add(REBAR_GUN);
                entries.add(RIFLE);
                entries.add(BOOM_BOX);
                entries.add(JETPACK);

                entries.add(REBAR);
                entries.add(REBAR_EXPLOSIVE);
                entries.add(REBAR_SHATTER);
                entries.add(REBAR_STUN);

                entries.add(RIFLE_AMMO);
                entries.add(RIFLE_AMMO_TURBO);
                entries.add(RIFLE_AMMO_HOMING);

                entries.add(BIOMASS);
                entries.add(SOLID_BIOFUEL);
                entries.add(FLUID_CANISTER);
                entries.add(PACKAGED_BIOFUEL);
                entries.add(PACKAGED_FUEL);
                entries.add(PACKAGED_TURBOFUEL);
                entries.add(PACKAGED_ROCKET_FUEL);
                entries.add(PACKAGED_IONIZED_FUEL);
            })
    );

    public static final ItemGroup PARTS = registerItemGroup("parts", Items.IRON_INGOT,
            ((displayContext, entries) -> {
                entries.add(BIOMASS);
                entries.add(SOLID_BIOFUEL);
                entries.add(FLUID_CANISTER);
                entries.add(PACKAGED_BIOFUEL);
                entries.add(PACKAGED_FUEL);
                entries.add(PACKAGED_TURBOFUEL);
                entries.add(PACKAGED_ROCKET_FUEL);
                entries.add(PACKAGED_IONIZED_FUEL);
            })
    );

    private static ItemGroup registerItemGroup(String id, Item icon, EntryCollector entryCollector) {
        return Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, id),
                FabricItemGroup.builder().displayName(Text.translatable("item.group.%s.%s".formatted(MOD_ID, id)))
                        .icon(() -> new ItemStack(icon)).entries(entryCollector).build());
    }

    public static void register() {
        LOGGER.debug("registering item groups for {}", MOD_ID);
    }
}
