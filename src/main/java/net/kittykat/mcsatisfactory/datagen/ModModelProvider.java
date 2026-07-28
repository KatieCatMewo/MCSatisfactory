package net.kittykat.mcsatisfactory.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.item.ModItems.*;

public class ModModelProvider extends FabricModelProvider {
    private static final TextureKey TEX0 = TextureKey.of("0");

    private static final Model M_FLUID_CANISTER = makeModelTemplate("fluid_canister");
    private static final Model M_REBAR          = makeModelTemplate("rebar");
    private static final Model M_RIFLE_AMMO     = makeModelTemplate("rifle_ammo");

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @NotNull
    @Contract("_ -> new")
    private static Model makeModelTemplate(String id) {
        return new Model(Optional.of(new Identifier(MOD_ID, "item/template/%s".formatted(id))), Optional.empty(), TEX0);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        addModel(generator, M_FLUID_CANISTER, FLUID_CANISTER);
        addModel(generator, M_FLUID_CANISTER, PACKAGED_BIOFUEL);
        addModel(generator, M_FLUID_CANISTER, PACKAGED_FUEL);
        addModel(generator, M_FLUID_CANISTER, PACKAGED_TURBOFUEL);

        addModel(generator, M_REBAR, REBAR);
        addModel(generator, M_REBAR, REBAR_EXPLOSIVE);
        addModel(generator, M_REBAR, REBAR_SHATTER);
        addModel(generator, M_REBAR, REBAR_STUN);

        addModel(generator, M_RIFLE_AMMO, RIFLE_AMMO);
        addModel(generator, M_RIFLE_AMMO, RIFLE_AMMO_TURBO);
        addModel(generator, M_RIFLE_AMMO, RIFLE_AMMO_HOMING);
    }

    private static void addModel(@NotNull ItemModelGenerator generator, @NotNull Model parent, Item item) {
        parent.upload(ModelIds.getItemModelId(item), TextureMap.of(TEX0, TextureMap.getId(item)), generator.writer);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

    }
}
