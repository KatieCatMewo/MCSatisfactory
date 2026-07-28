package net.kittykat.mcsatisfactory;

import net.fabricmc.api.ModInitializer;
import net.kittykat.mcsatisfactory.block.ModBlocks;
import net.kittykat.mcsatisfactory.events.ModEvents;
import net.kittykat.mcsatisfactory.item.ModItemGroups;
import net.kittykat.mcsatisfactory.item.ModItems;
import net.kittykat.mcsatisfactory.networking.ModNetworking;
import net.kittykat.mcsatisfactory.particle.ModParticles;
import net.kittykat.mcsatisfactory.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCSatisfactory implements ModInitializer {
    public static final String MOD_ID = "mcsatisfactory";
    public static final Logger LOGGER = LoggerFactory.getLogger("MC Satisfactory");

    @Override
    public void onInitialize() {
        LOGGER.info("Welcome to FICSIT Incorporated!");

        ModEvents.register();
        ModBlocks.register();
        ModItems.register();
        ModItemGroups.register();
        ModSounds.register();
        ModNetworking.register();
        ModParticles.registerParticles();
    }
}
