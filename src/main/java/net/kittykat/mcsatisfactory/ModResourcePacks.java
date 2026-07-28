package net.kittykat.mcsatisfactory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

@Environment(EnvType.CLIENT)
public abstract class ModResourcePacks {
    private static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();

    public static void register() {
        LOGGER.debug("registering resource packs for {}", MOD_ID);

        registerPack("gui_compatibility", ResourcePackActivationType.ALWAYS_ENABLED);
    }

    private static void registerPack(String id, ResourcePackActivationType activationType) {
        ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(MOD_ID, id), MOD_CONTAINER,
                Text.translatable("pack.%s.%s".formatted(MOD_ID, id)), activationType);
    }
}
