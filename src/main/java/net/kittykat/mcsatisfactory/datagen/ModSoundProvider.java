package net.kittykat.mcsatisfactory.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.sound.ModSounds.*;

public class ModSoundProvider implements DataProvider {
    private final FabricDataOutput output;
    private final JsonObject       json = new JsonObject();

    public ModSoundProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        generateSoundEntries();

        Path path = output.getPath().resolve("assets/%s/sounds.json".formatted(MOD_ID));
        return DataProvider.writeToPath(writer, json, path);
    }

    private void generateSoundEntries() {
        addUiSound(   HUB_LAUNCH_BUTTON_REVEAL);
        addUiSound(   HUB_LAUNCH_BUTTON_PRESS);
        addUiSound(   RESOURCE_SCAN_START);
        addUiSound(   RESOURCE_SCAN_FIRE);
        addSound(     RESOURCE_SCAN_HIT, 4096, true, 0, false);
        addWorldSound(SLOT_BACK_EQUIP);
        addWorldSound(SLOT_BACK_UNEQUIP);
        addUiSound(   MAM_DONE_JINGLE);
        addWorldSound(MAM_RESEARCH_LOOP, true);

        addWorldSound(JETPACK_ACTIVATE);
        addWorldSound(JETPACK_ACTIVE_LOOP, true);
        addWorldSound(JETPACK_NO_FUEL);
        addWorldSound(JETPACK_THROTTLE_OFF);

        addWorldSound(HOVER_PACK_ENGINE_START);
        addWorldSound(HOVER_PACK_ENGINE_LOOP_1,  true);
        addWorldSound(HOVER_PACK_ENGINE_LOOP_2,  true);
        addWorldSound(HOVER_PACK_SLOW_FALL_LOOP, true);
        addWorldSound(HOVER_PACK_POWER_CONNECT);
        addWorldSound(HOVER_PACK_POWER_LOSS);

        addUiSound(   POWER_LOSS);
        addUiSound(   POWER_FUSE_RESET);
        addUiSound(   FUSE_LEVER_LOCK);
        addUiSound(   FUSE_LEVER_FAIL);
        addUiSound(   FUSE_LEVER_CLICK_UP,   7);
        addUiSound(   FUSE_LEVER_CLICK_DOWN, 13);
        addWorldSound(POWER_SWITCH_ON);
        addWorldSound(POWER_SWITCH_OFF);
        addWorldSound(POWER_LINE_CONNECT,    3);

        addUiSound(   BOOM_BOX_UI_PLAY);
        addUiSound(   BOOM_BOX_UI_STOP);
        addUiSound(   BOOM_BOX_UI_CHANGE);
        addUiSound(   BOOM_BOX_UI_NEXT);
        addUiSound(   BOOM_BOX_UI_PREV);
        addWorldSound(BOOM_BOX_TURBO_BASS);
        addBoomBoxTrack(BOOM_BOX_TIER1);
        addBoomBoxTrack(BOOM_BOX_NOSTALGIUM);
        addBoomBoxTrack(BOOM_BOX_HYPERFOCUS);
        addBoomBoxTrack(BOOM_BOX_DESERT_CHILL);
        addBoomBoxTrack(BOOM_BOX_GOAT_CHILL);
        addBoomBoxTrack(BOOM_BOX_GOAT_STORM);
        addBoomBoxTrack(BOOM_BOX_GOATSHIRE);
        addBoomBoxTrack(BOOM_BOX_WASTE_OF_SPACE);
        addBoomBoxTrack(BOOM_BOX_CAVE_FLOWERS);
        addBoomBoxTrack(BOOM_BOX_ME_AND_MY_GOLF_CART);
        addBoomBoxTrack(BOOM_BOX_STOFES_THEME);
        addBoomBoxTrack(BOOM_BOX_THEY_CALL_ME_LILLBOXEN);
        addBoomBoxTrack(BOOM_BOX_CLASSIC_MICHAEL);
        addBoomBoxTrack(BOOM_BOX_MILKY_MCHOUSECAT);
        addBoomBoxTrack(BOOM_BOX_CORE_GUARDIANS);
        addBoomBoxTrack(BOOM_BOX_EXPLORER);
        addBoomBoxTrack(BOOM_BOX_LOEK3);
        addBoomBoxTrack(BOOM_BOX_SKYES_THEME1);
        addBoomBoxTrack(BOOM_BOX_THE_WANDERERS);
        addBoomBoxTrack(BOOM_BOX_IMPENDING_DOOM);
        addBoomBoxTrack(BOOM_BOX_RUINS_OF_BRIGHTHOLME);
        addBoomBoxTrack(BOOM_BOX_SKYES_THEME2);
        addBoomBoxTrack(BOOM_BOX_STELLAR);
        addBoomBoxTrack(BOOM_BOX_DECEIVED_BY_LIGHT);
        addBoomBoxTrack(BOOM_BOX_FOLLOW_MOLLY);
        addBoomBoxTrack(BOOM_BOX_LETS_GO_DEEPER);
        addBoomBoxTrack(BOOM_BOX_ODE_TO_THE_FALLEN);
        addBoomBoxTrack(BOOM_BOX_ROBOT_GETAWAY);
        addBoomBoxTrack(BOOM_BOX_THE_INVASION);
        addBoomBoxTrack(BOOM_BOX_MAIN_THEME);
        addBoomBoxTrack(BOOM_BOX_ADVENTURE4);
        addBoomBoxTrack(BOOM_BOX_ADVENTURE5);
        addBoomBoxTrack(BOOM_BOX_ADVENTURE6);
        addBoomBoxTrack(BOOM_BOX_ADVENTURE12);
        addBoomBoxTrack(BOOM_BOX_HUNTDOWN_MAIN);
        addBoomBoxTrack(BOOM_BOX_WOLF_CUBS);
        addBoomBoxTrack(BOOM_BOX_GAME_DAY);
        addBoomBoxTrack(BOOM_BOX_NEW_HUNTING_GROUNDS);
        addBoomBoxTrack(BOOM_BOX_GOONS_OF_TOMORROW);
        addBoomBoxTrack(OST_ACCELERATOR);
        addBoomBoxTrack(OST_ZEIGARNIK_EFFECT);

        addUiSound(INCOMING_MESSAGE);
    }

    private void addUiSound(SoundEvent sound) {
        addUiSound(sound, 0);
    }
    private void addUiSound(SoundEvent sound, int soundFileCount) {
        addSound(sound, 16, false, soundFileCount, false);
    }

    private void addWorldSound(SoundEvent sound) {
        addWorldSound(sound, false);
    }
    private void addWorldSound(SoundEvent sound, int soundFileCount) {
        addWorldSound(sound, soundFileCount, false);
    }
    private void addWorldSound(SoundEvent sound, boolean stream) {
        addWorldSound(sound, 0, stream);
    }
    private void addWorldSound(SoundEvent sound, int soundFileCount, boolean stream) {
        addSound(sound, 16, !stream, soundFileCount, stream);
    }

    private void addBoomBoxTrack(SoundEvent sound) {
        addSound(sound, 32, false, 0, true);
    }

    private void addSound(SoundEvent sound, int distance,
                                 boolean hasSubtitles, int soundFileCount, boolean stream) {
        JsonObject soundEntry = new JsonObject();

        if (hasSubtitles) {
            soundEntry.addProperty("subtitle", "subtitle.%s.%s".formatted(MOD_ID, sound.getId().getPath()));
        }

        JsonArray soundReferences = new JsonArray();
        if (soundFileCount == 0) {
            addSoundRef(soundReferences, sound, distance, stream);
        } else {
            String fileID = getSoundFileID(sound);
            for (int f = 1; f <= soundFileCount; f++) {
                addSoundRef(soundReferences, "%s_%02d".formatted(fileID, f), distance, stream);
            }
        }
        soundEntry.add("sounds", soundReferences);

        json.add(sound.getId().getPath(), soundEntry);
    }

    @NotNull
    private static String getSoundFileID(@NotNull SoundEvent sound) {
        return sound.getId().toString().replace(".", "/");
    }

    private static void addSoundRef(JsonArray references, SoundEvent sound, int distance, boolean stream) {
        addSoundRef(references, getSoundFileID(sound), distance, stream);
    }
    private static void addSoundRef(JsonArray references, String file, int distance, boolean stream) {
        if (!stream && (distance == 16)) {
            references.add(file);
        } else {
            JsonObject soundRef = new JsonObject();
            soundRef.addProperty("name",   file);
            if (distance != 16) {
                soundRef.addProperty("attenuation_distance", distance);
            }
            if (stream) {
                soundRef.addProperty("stream", true);
            }
            references.add(soundRef);
        }
    }

    @Override
    public String getName() {
        return "sounds.json Stuff";
    }
}
