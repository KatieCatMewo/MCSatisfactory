package net.kittykat.mcsatisfactory.events;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.event.GameEvent;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public abstract class ModEvents {
    public static final GameEvent JETPACK_FLY = registerEvent("jetpack_fly");

    private static GameEvent registerEvent(String id, int range) {
        return Registry.register(Registries.GAME_EVENT, id, new GameEvent(id, range));
    }
    private static GameEvent registerEvent(String id) {
        return registerEvent(id, GameEvent.DEFAULT_RANGE);
    }

    public static void register() {
        LOGGER.debug("registering game events for {}", MOD_ID);
    }
}
