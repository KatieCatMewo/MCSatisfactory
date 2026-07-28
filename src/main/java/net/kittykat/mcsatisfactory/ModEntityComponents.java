package net.kittykat.mcsatisfactory;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.kittykat.mcsatisfactory.components.ModComponents;
import net.kittykat.mcsatisfactory.components.JetpackDataComponent;
import net.kittykat.mcsatisfactory.components.PreferredAmmoComponent;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.components.ModComponents.*;

public class ModEntityComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(@NotNull EntityComponentFactoryRegistry registry) {
        ModComponents.register();
        registry.registerForPlayers(JETPACK_DATA,   p -> new JetpackDataComponent(),   RespawnCopyStrategy.CHARACTER);
        registry.registerForPlayers(PREFERRED_AMMO, p -> new PreferredAmmoComponent(), RespawnCopyStrategy.CHARACTER);
    }
}
