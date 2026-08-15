package net.kittykat.mcsatisfactory.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.kittykat.mcsatisfactory.controls.SpecialKey;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.controls.ModControls.*;
import static net.kittykat.mcsatisfactory.item.ModItems.*;
import static net.kittykat.mcsatisfactory.sound.ModSounds.*;

public class ModLangProvider extends FabricLanguageProvider {
    private TranslationBuilder builder;

    protected ModLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        builder = translationBuilder;
        generateLang();
    }

    private void generateLang() {
        addLang("pack", "gui_compatibility", "GUI Compatibility");

        addLang(KEY_CATEGORY,   "MC Satisfactory");
        addKey(SCAN_RESOURCES,  "Scan Resources");
        addKey(OPEN_BUILD_MENU, "Open Build Menu");
        addKey(RELOAD,          "Reload Weapon");

        addTooltip("change_fuel", "§8Press [§7R-Click§8] to change fuel");

        addSubtitle(RESOURCE_SCAN_HIT, "Resource Scan hits");
        addSubtitle(SLOT_BACK_EQUIP,   "Equipment Rattles");
        addSubtitle(SLOT_BACK_UNEQUIP, "Equipment Rattles");
        addSubtitle(JETPACK_ACTIVATE,     "Jetpack activates");
        addSubtitle(JETPACK_NO_FUEL,      "Jetpack runs out of fuel");
        addSubtitle(JETPACK_THROTTLE_OFF, "Jetpack throttles down");
        addSubtitle(HOVER_PACK_ENGINE_START,  "Hover Pack starts");
        addSubtitle(HOVER_PACK_POWER_CONNECT, "Hover Pack connects");
        addSubtitle(HOVER_PACK_POWER_LOSS,    "Hover Pack looses power");
        addSubtitle(POWER_SWITCH_ON,    "Power Switch turns on");
        addSubtitle(POWER_SWITCH_OFF,   "Power Switch turns off");
        addSubtitle(POWER_LINE_CONNECT, "Power Line connects");
        addSubtitle(BOOM_BOX_TURBO_BASS, "Turbo Bass triggers");

        addItemGroup("equipment", "Satisfactory Equipment");
        addItemGroup("parts",     "Satisfactory Parts");

        addItem(XENO_ZAPPER,        "Xeno-Zapper");
        addItem(NOBELISK_DETONATOR, "Nobelisk Detonator");
        addItem(REBAR_GUN,          "Rebar Gun");
        addItem(RIFLE,              "Rifle");
        addItem(BOOM_BOX,           "Boom Box");
        addItem(JETPACK,            "Jetpack");
        addItem(REBAR,           "Rebar");
        addItem(REBAR_EXPLOSIVE, "Explosive Rebar");
        addItem(REBAR_SHATTER,   "Shatter Rebar");
        addItem(REBAR_STUN,      "Stun Rebar");
        addItem(RIFLE_AMMO,        "Rifle Ammo");
        addItem(RIFLE_AMMO_TURBO,  "Turbo Rifle Ammo");
        addItem(RIFLE_AMMO_HOMING, "Homing Rifle Ammo");
        addItem(BIOMASS,               "Biomass");
        addItem(SOLID_BIOFUEL,         "Solid Biofuel");
        addItem(FLUID_CANISTER,        "Empty Canister");
        addItem(PACKAGED_BIOFUEL,      "Packaged Liquid Biofuel");
        addItem(PACKAGED_FUEL,         "Packaged Fuel");
        addItem(PACKAGED_TURBOFUEL,    "Packaged Turbofuel");
        addItem(PACKAGED_ROCKET_FUEL,  "Packaged Rocket Fuel");
        addItem(PACKAGED_IONIZED_FUEL, "Packaged Ionized Fuel");
    }

    private void addItem(@NotNull Item item, String translation) {
        addLang(item.getTranslationKey(), translation);
    }
    private void addItemGroup(String group, String translation) {
        addLang("item.group", group, translation);
    }

    private void addTooltip(String tooltip, String translation) {
        addLang("tooltip", tooltip, translation);
    }

    private void addKey(@NotNull KeyBinding key, String translation) {
        addLang(key.getTranslationKey(), translation);
    }
    private void addKey(@NotNull SpecialKey key, String translation) {
        addKey(key.key, translation);
    }

    private void addSubtitle(@NotNull SoundEvent sound, String translation) {
        addLang("subtitle", sound.getId().getPath(), translation);
    }

    private void addDialogue(String dialogue, String translation) {
        addLang("dialogue", dialogue, translation);
    }

    private void addLang(String key, String translation) {
        builder.add(key, translation);
    }
    private void addLang(String type, String id, String translation) {
        addLang("%s.%s.%s".formatted(type, MOD_ID, id), translation);
    }
}
