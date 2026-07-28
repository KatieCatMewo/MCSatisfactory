package net.kittykat.mcsatisfactory.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public abstract class ModComponents {
    public static final ComponentKey<JetpackDataComponent>   JETPACK_DATA
            = registerComponent("jetpack_data",   JetpackDataComponent.class);
    public static final ComponentKey<PreferredAmmoComponent> PREFERRED_AMMO
            = registerComponent("preferred_ammo", PreferredAmmoComponent.class);

    private static <C extends Component> @NotNull ComponentKey<C> registerComponent(String id, Class<C> componentClass) {
        return ComponentRegistry.getOrCreate(new Identifier(MOD_ID, id), componentClass);
    }

    public static void register() {
        LOGGER.debug("registering cardinal-components components for {}", MOD_ID);
    }
}
