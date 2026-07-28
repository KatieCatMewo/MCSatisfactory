package net.kittykat.mcsatisfactory.render;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public abstract class ModRendering {
    public static void register() {
        ModRenderLayer.register();
        LOGGER.debug("registering render events for {}", MOD_ID);

        HudRenderCallback.EVENT.register(new SatisfactoryHUD());

        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ScannerPulseRenderer.INSTANCE.render(context, client);
        });
    }
}
