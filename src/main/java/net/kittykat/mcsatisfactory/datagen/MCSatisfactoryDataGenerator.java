package net.kittykat.mcsatisfactory.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import org.jetbrains.annotations.NotNull;

public class MCSatisfactoryDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(@NotNull FabricDataGenerator fabricDataGenerator) {
        Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModSoundProvider::new);
        pack.addProvider(ModLangProvider::new);
    }
}
