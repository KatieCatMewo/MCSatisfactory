package net.kittykat.mcsatisfactory.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.kittykat.mcsatisfactory.components.item.*;
import net.kittykat.mcsatisfactory.components.entity.*;
import net.kittykat.mcsatisfactory.components.item.weapon.RebarGunDataComponent;
import net.kittykat.mcsatisfactory.components.item.weapon.RifleDataComponent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public abstract class ModComponents {
    // Entity Components
    public static final ComponentKey<PreferredFuelComponent> PREFERRED_FUEL
            = registerComponent("preferred_fuel", PreferredFuelComponent.class);

    // Item Components
    public static final ComponentKey<JetpackDataComponent> JETPACK_DATA
            = registerComponent("jetpack_data",   JetpackDataComponent.class);
    public static final ComponentKey<RebarGunDataComponent> REBAR_GUN_DATA
            = registerComponent("rebar_gun_data", RebarGunDataComponent.class);
    public static final ComponentKey<RifleDataComponent> RIFLE_DATA
            = registerComponent("rifle_data",     RifleDataComponent.class);

    private static <C extends Component> @NotNull ComponentKey<C> registerComponent(String id, Class<C> componentClass) {
        return ComponentRegistry.getOrCreate(new Identifier(MOD_ID, id), componentClass);
    }

    public static void register(String type) {
        LOGGER.debug("registering cca {} components for {}", type, MOD_ID);
    }
}
