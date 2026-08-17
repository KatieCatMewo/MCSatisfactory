package net.kittykat.mcsatisfactory.components.entity;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.kittykat.mcsatisfactory.components.ModComponents;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.components.ModComponents.*;

public class ModEntityComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(@NotNull EntityComponentFactoryRegistry registry) {
        ModComponents.register("entity");
        registry.registerForPlayers(PREFERRED_FUEL, player -> new PreferredFuelComponent(), RespawnCopyStrategy.CHARACTER);
    }
}
