package net.kittykat.mcsatisfactory.controls;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public interface KeyHandler {
    void shortPress(MinecraftClient client);

    void longPress(MinecraftClient client);
    void longReleased(MinecraftClient client);
}
