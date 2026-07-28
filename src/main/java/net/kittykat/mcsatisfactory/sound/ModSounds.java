package net.kittykat.mcsatisfactory.sound;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;

public abstract class ModSounds {
    public static final SoundEvent HUB_LAUNCH_BUTTON_REVEAL = registerSound("hub_launch_button_reveal");
    public static final SoundEvent HUB_LAUNCH_BUTTON_PRESS  = registerSound("hub_launch_button_press");
    public static final SoundEvent RESOURCE_SCAN_START      = registerSound("resource_scan_start");
    public static final SoundEvent RESOURCE_SCAN_FIRE       = registerSound("resource_scan_fire");
    public static final SoundEvent RESOURCE_SCAN_HIT        = registerSound("resource_scan_hit", 2048);
    public static final SoundEvent SLOT_BACK_EQUIP          = registerSound("slot_back_equip");
    public static final SoundEvent SLOT_BACK_UNEQUIP        = registerSound("slot_back_unequip");
    public static final SoundEvent MAM_DONE_JINGLE          = registerSound("mam_jingle");
    public static final SoundEvent MAM_RESEARCH_LOOP        = registerSound("mam_research_loop");

    public static final SoundEvent JETPACK_ACTIVATE     = registerSound("jetpack.activate");
    public static final SoundEvent JETPACK_ACTIVE_LOOP  = registerSound("jetpack.active_loop");
    public static final SoundEvent JETPACK_NO_FUEL      = registerSound("jetpack.no_fuel");
    public static final SoundEvent JETPACK_THROTTLE_OFF = registerSound("jetpack.throttle_off");

    public static final SoundEvent HOVER_PACK_ENGINE_START   = registerSound("hover_pack.engine_start");
    public static final SoundEvent HOVER_PACK_ENGINE_LOOP_1  = registerSound("hover_pack.engine_loop_1");
    public static final SoundEvent HOVER_PACK_ENGINE_LOOP_2  = registerSound("hover_pack.engine_loop_2");
    public static final SoundEvent HOVER_PACK_SLOW_FALL_LOOP = registerSound("hover_pack.slow_fall_loop");
    public static final SoundEvent HOVER_PACK_POWER_CONNECT  = registerSound("hover_pack.power_connect");
    //public static final SoundEvent HOVER_PACK_POWER_WARNING  = registerSound("hover_pack.power_warning");
    public static final SoundEvent HOVER_PACK_POWER_LOSS     = registerSound("hover_pack.power_loss");

    public static final SoundEvent POWER_LOSS            = registerSound("power.power_loss");
    public static final SoundEvent POWER_FUSE_RESET      = registerSound("power.fuse_reset");
    public static final SoundEvent FUSE_LEVER_LOCK       = registerSound("power.fuse_lever.lock");
    public static final SoundEvent FUSE_LEVER_FAIL       = registerSound("power.fuse_lever.fail");
    public static final SoundEvent FUSE_LEVER_CLICK_UP   = registerSound("power.fuse_lever.click_up");
    public static final SoundEvent FUSE_LEVER_CLICK_DOWN = registerSound("power.fuse_lever.click_down");
    public static final SoundEvent POWER_SWITCH_ON       = registerSound("power.power_switch_on");
    public static final SoundEvent POWER_SWITCH_OFF      = registerSound("power.power_switch_off");
    public static final SoundEvent POWER_LINE_CONNECT    = registerSound("power.power_line_connect");

    public static final SoundEvent BOOM_BOX_UI_PLAY   = registerSound("boom_box.ui.play");
    public static final SoundEvent BOOM_BOX_UI_STOP   = registerSound("boom_box.ui.stop");
    public static final SoundEvent BOOM_BOX_UI_CHANGE = registerSound("boom_box.ui.change_tape");
    public static final SoundEvent BOOM_BOX_UI_NEXT   = registerSound("boom_box.ui.next");
    public static final SoundEvent BOOM_BOX_UI_PREV   = registerSound("boom_box.ui.previous");
    public static final SoundEvent BOOM_BOX_TURBO_BASS = registerSound("boom_box.turbo_bass_trigger");
    public static final SoundEvent BOOM_BOX_TIER1      = registerTrack("boom_box.absolute_ficsit.tier1");
    public static final SoundEvent BOOM_BOX_NOSTALGIUM = registerTrack("boom_box.absolute_ficsit.nostalgium");
    public static final SoundEvent BOOM_BOX_HYPERFOCUS = registerTrack("boom_box.absolute_ficsit.hyperfocus");
    public static final SoundEvent BOOM_BOX_DESERT_CHILL   = registerTrack("boom_box.worst_of_goat_sim.desert_chill");
    public static final SoundEvent BOOM_BOX_GOAT_CHILL     = registerTrack("boom_box.worst_of_goat_sim.goat_chill");
    public static final SoundEvent BOOM_BOX_GOAT_STORM     = registerTrack("boom_box.worst_of_goat_sim.goat_storm");
    public static final SoundEvent BOOM_BOX_GOATSHIRE      = registerTrack("boom_box.worst_of_goat_sim.goatshire");
    public static final SoundEvent BOOM_BOX_WASTE_OF_SPACE = registerTrack("boom_box.worst_of_goat_sim.waste_of_space");
    public static final SoundEvent BOOM_BOX_CAVE_FLOWERS        = registerTrack("boom_box.joel_syntholm.cave_flowers");
    public static final SoundEvent BOOM_BOX_ME_AND_MY_GOLF_CART = registerTrack("boom_box.joel_syntholm.me_and_my_golf_cart");
    public static final SoundEvent BOOM_BOX_STOFES_THEME        = registerTrack("boom_box.joel_syntholm.stoffes_theme");
    public static final SoundEvent BOOM_BOX_THEY_CALL_ME_LILLBOXEN = registerTrack("boom_box.le_michael.they_call_me_lillboxen");
    public static final SoundEvent BOOM_BOX_CLASSIC_MICHAEL        = registerTrack("boom_box.le_michael.classic_michael");
    public static final SoundEvent BOOM_BOX_MILKY_MCHOUSECAT       = registerTrack("boom_box.le_michael.milky_mchousecat");
    public static final SoundEvent BOOM_BOX_CORE_GUARDIANS = registerTrack("boom_box.sanctum.core_guardians");
    public static final SoundEvent BOOM_BOX_EXPLORER       = registerTrack("boom_box.sanctum.explorer");
    public static final SoundEvent BOOM_BOX_LOEK3          = registerTrack("boom_box.sanctum.loek3");
    public static final SoundEvent BOOM_BOX_SKYES_THEME1   = registerTrack("boom_box.sanctum.skyes_theme");
    public static final SoundEvent BOOM_BOX_THE_WANDERERS  = registerTrack("boom_box.sanctum.the_wanderers");
    public static final SoundEvent BOOM_BOX_IMPENDING_DOOM       = registerTrack("boom_box.sanctum2.impending_doom");
    public static final SoundEvent BOOM_BOX_RUINS_OF_BRIGHTHOLME = registerTrack("boom_box.sanctum2.ruins_of_brightholme");
    public static final SoundEvent BOOM_BOX_SKYES_THEME2         = registerTrack("boom_box.sanctum2.skyes_theme2");
    public static final SoundEvent BOOM_BOX_STELLAR              = registerTrack("boom_box.sanctum2.stellar");
    public static final SoundEvent BOOM_BOX_DECEIVED_BY_LIGHT = registerTrack("boom_box.deep_rock_galactic.deceived_by_light");
    public static final SoundEvent BOOM_BOX_FOLLOW_MOLLY      = registerTrack("boom_box.deep_rock_galactic.follow_molly");
    public static final SoundEvent BOOM_BOX_LETS_GO_DEEPER    = registerTrack("boom_box.deep_rock_galactic.lets_go_deeper");
    public static final SoundEvent BOOM_BOX_ODE_TO_THE_FALLEN = registerTrack("boom_box.deep_rock_galactic.ode_to_the_fallen");
    public static final SoundEvent BOOM_BOX_ROBOT_GETAWAY     = registerTrack("boom_box.deep_rock_galactic.robot_getaway");
    public static final SoundEvent BOOM_BOX_THE_INVASION = registerTrack("boom_box.songs_of_conquest.the_invasion");
    public static final SoundEvent BOOM_BOX_MAIN_THEME   = registerTrack("boom_box.songs_of_conquest.main_theme");
    public static final SoundEvent BOOM_BOX_ADVENTURE4   = registerTrack("boom_box.songs_of_conquest.adventure4");
    public static final SoundEvent BOOM_BOX_ADVENTURE5   = registerTrack("boom_box.songs_of_conquest.adventure5");
    public static final SoundEvent BOOM_BOX_ADVENTURE6   = registerTrack("boom_box.songs_of_conquest.adventure6");
    public static final SoundEvent BOOM_BOX_ADVENTURE12  = registerTrack("boom_box.songs_of_conquest.adventure12");
    public static final SoundEvent BOOM_BOX_HUNTDOWN_MAIN       = registerTrack("boom_box.huntdown.huntdown_main");
    public static final SoundEvent BOOM_BOX_WOLF_CUBS           = registerTrack("boom_box.huntdown.wolf_cubs");
    public static final SoundEvent BOOM_BOX_GAME_DAY            = registerTrack("boom_box.huntdown.game_day");
    public static final SoundEvent BOOM_BOX_NEW_HUNTING_GROUNDS = registerTrack("boom_box.huntdown.new_hunting_grounds");
    public static final SoundEvent BOOM_BOX_GOONS_OF_TOMORROW   = registerTrack("boom_box.huntdown.goons_of_tomorrow");
    public static final SoundEvent OST_ACCELERATOR      = registerTrack("boom_box.ost.accelerator");
    public static final SoundEvent OST_ZEIGARNIK_EFFECT = registerTrack("boom_box.ost.zeigarnik_effect");

    public static final SoundEvent INCOMING_MESSAGE = registerSound("ada.incoming_message");

    private static SoundEvent registerTrack(String id) {
        return registerSound(id, 32);
    }
    private static SoundEvent registerSound(String id) {
        return registerSound(id, 16);
    }
    private static SoundEvent registerSound(String id, int maxDistance) {
        Identifier soundID = new Identifier(MOD_ID, id);
        SoundEvent sound = (maxDistance != 16) ? SoundEvent.of(soundID, maxDistance) : SoundEvent.of(soundID);
        return Registry.register(Registries.SOUND_EVENT, soundID, sound);
    }

    public static void register() {
        LOGGER.debug("registering sounds for {}", MOD_ID);
    }

    public static void playUiSound(SoundEvent sound, @NotNull SoundManager soundManager, SoundCategory category) {
        soundManager.play(new UiSoundInstance(sound, category, Random.create()));
    }
    public static void stopSounds(@NotNull SoundEvent sound, @NotNull SoundManager soundManager,
                                  @Nullable SoundCategory category) {
        soundManager.stopSounds(sound.getId(), category);
    }
}
