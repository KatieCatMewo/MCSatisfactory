package net.kittykat.mcsatisfactory.controls;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class TestKeyHandler implements KeyHandler {
    @Override
    public void shortPress(MinecraftClient client) {
        System.out.println("short press");
    }

    @Override
    public void longPress(MinecraftClient client) {
        System.out.println("long press");
    }
    @Override
    public void longReleased(MinecraftClient client) {
        System.out.println("long released");
    }
}
