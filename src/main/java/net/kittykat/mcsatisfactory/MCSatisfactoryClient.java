package net.kittykat.mcsatisfactory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kittykat.mcsatisfactory.controls.ModControls;
import net.kittykat.mcsatisfactory.tools.ResourceScanner;
import net.kittykat.mcsatisfactory.render.ModRendering;
import net.kittykat.mcsatisfactory.particle.ModParticles;

@Environment(EnvType.CLIENT)
public class MCSatisfactoryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRendering.register();
        ModControls.register();
        ModParticles.registerFactories();
        ModResourcePacks.register();

        ClientPlayConnectionEvents.DISCONNECT.register((
                (handler, client) -> ResourceScanner.INSTANCE.stop()));
    }
}
