package net.kittykat.mcsatisfactory.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kittykat.mcsatisfactory.item.equipment.*;
import net.kittykat.mcsatisfactory.item.equipment.weapon.*;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;

public abstract class ModItems {
    public static final Item COFFEE_CUP        = registerItem("coffee_cup",        CoffeeCupItem::new);
    public static final Item GOLDEN_COFFEE_CUP = registerItem("golden_coffee_cup", CoffeeCupItem::new,
            new FabricItemSettings().rarity(Rarity.EPIC));

    public static final Item XENO_ZAPPER        = registerItem("xeno_zapper",        XenoZapperItem::new);
    public static final Item NOBELISK_DETONATOR = registerItem("nobelisk_detonator", NobeliskDetonatorItem::new);
    public static final Item REBAR_GUN          = registerItem("rebar_gun",          RebarGunItem::new);
    public static final Item RIFLE              = registerItem("rifle",              RifleItem::new);
    public static final Item BOOM_BOX           = registerItem("boom_box",           BoomBoxItem::new);
    public static final Item JETPACK            = registerItem("jetpack",            JetpackItem::new);

    public static final Item REBAR           = registerGeneric("rebar");
    public static final Item REBAR_EXPLOSIVE = registerGeneric("rebar_explosive");
    public static final Item REBAR_SHATTER   = registerGeneric("rebar_shatter");
    public static final Item REBAR_STUN      = registerGeneric("rebar_stun");

    public static final Item RIFLE_AMMO        = registerGeneric("rifle_ammo");
    public static final Item RIFLE_AMMO_TURBO  = registerGeneric("rifle_ammo_turbo");
    public static final Item RIFLE_AMMO_HOMING = registerGeneric("rifle_ammo_homing");

    public static final Item BIOMASS               = registerGeneric("biomass");
    public static final Item SOLID_BIOFUEL         = registerGeneric("solid_biofuel");
    public static final Item FLUID_CANISTER        = registerGeneric("fluid_canister");
    public static final Item PACKAGED_BIOFUEL      = registerGeneric("packaged_biofuel");
    public static final Item PACKAGED_FUEL         = registerGeneric("packaged_fuel");
    public static final Item PACKAGED_TURBOFUEL    = registerGeneric("packaged_turbofuel");
    public static final Item PACKAGED_ROCKET_FUEL  = registerGeneric("packaged_rocket_fuel");
    public static final Item PACKAGED_IONIZED_FUEL = registerGeneric("packaged_ionized_fuel");

    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, id), item);
    }
    private static <I extends Item> Item registerItem(String id, @NotNull ItemFactory<I> itemFactory, Item.Settings settings) {
        return registerItem(id, itemFactory.make(settings));
    }
    private static <I extends Item> Item registerItem(String id, @NotNull ItemFactory<I> itemFactory) {
        return registerItem(id, itemFactory, new FabricItemSettings());
    }
    private static Item registerGeneric(String id, Settings settings) {
        return registerItem(id, new Item(settings));
    }
    private static Item registerGeneric(String id) {
        return registerGeneric(id, new FabricItemSettings());
    }

    @FunctionalInterface
    private interface ItemFactory<I> {
        I make(Settings settings);
    }

    public static void register() {
        LOGGER.debug("registering items for {}", MOD_ID);
    }
}
