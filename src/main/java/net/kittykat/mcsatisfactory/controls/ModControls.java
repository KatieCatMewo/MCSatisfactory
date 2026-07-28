package net.kittykat.mcsatisfactory.controls;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil.Type;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.minecraft.client.util.InputUtil.Type.*;
import static org.lwjgl.glfw.GLFW.*;

@Environment(EnvType.CLIENT)
public abstract class ModControls {
    private static final ArrayList<SpecialKey> SPECIAL_KEYS = new ArrayList<>();

    public static final String KEY_CATEGORY = "key.category.%s.main".formatted(MOD_ID);

    public static SpecialKey SCAN_RESOURCES  = registerSpecial("scan_resources", KEYSYM, GLFW_KEY_V, ResourceScanner.INSTANCE);
    public static KeyBinding OPEN_BUILD_MENU = registerControl("build_menu",     KEYSYM, GLFW_KEY_R);
    public static SpecialKey RELOAD          = registerSpecial("reload_weapon",  KEYSYM, GLFW_KEY_G, new TestKeyHandler());

    private static KeyBinding registerControl(String id, Type type, int defaultCode) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding("key.%s.%s".formatted(MOD_ID, id), type, defaultCode, KEY_CATEGORY));
    }
    @NotNull
    private static SpecialKey registerSpecial(String id, Type type, int defaultCode, KeyHandler keyHandler) {
        SpecialKey key = new SpecialKey(registerControl(id, type, defaultCode), keyHandler);
        SPECIAL_KEYS.add(key);
        return key;
    }

    public static void register() {
        LOGGER.debug("registering controls for {}", MOD_ID);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ResourceScanner.INSTANCE.tick(client);

            for (SpecialKey key : SPECIAL_KEYS) {
                key.tick(client);
            }

            if (OPEN_BUILD_MENU.wasPressed()) {
                // ToDo: open build menu / exit build mode
            }
        });
    }
}
