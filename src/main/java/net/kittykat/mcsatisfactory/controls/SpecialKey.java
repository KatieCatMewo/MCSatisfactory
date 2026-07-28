package net.kittykat.mcsatisfactory.controls;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

@Environment(EnvType.CLIENT)
public class SpecialKey {
    private static final int HOLD_TIME = 8;

    public  final KeyBinding key;
    private final KeyHandler keyHandler;

    private int heldTicks = 0;

    public SpecialKey(KeyBinding key, KeyHandler keyHandler) {
        this.key        = key;
        this.keyHandler = keyHandler;
    }

    protected void tick(MinecraftClient client) {
        if (key.isPressed()) {
            if (heldTicks < HOLD_TIME) {
                heldTicks++;
                if (heldTicks >= HOLD_TIME) {
                    keyHandler.longPress(client);
                }
            }
        } else if (heldTicks > 0) {
            if (heldTicks < HOLD_TIME) {
                keyHandler.shortPress(client);
            } else {
                keyHandler.longReleased(client);
            }
            heldTicks = 0;
        }
    }
}
