package net.kittykat.mcsatisfactory.networking.jetpack;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kittykat.mcsatisfactory.components.entity.PreferredFuelComponent;
import net.kittykat.mcsatisfactory.networking.ClientToServerPacket;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.kittykat.mcsatisfactory.components.ModComponents;
import org.jetbrains.annotations.NotNull;

public record JetpackFuelChangeC2SPacket() implements ClientToServerPacket {
    public JetpackFuelChangeC2SPacket(PacketByteBuf ignored) {
        this();
    }

    @Override
    public void write(PacketByteBuf buf) {}

    @Override
    public void handle(@NotNull MinecraftServer server, ServerPlayerEntity player,
                       ServerPlayNetworkHandler handler, PacketSender responseSender) {
        server.execute(() -> {
            PreferredFuelComponent data = ModComponents.PREFERRED_FUEL.get(player);
            data.nextFuel();
            ModComponents.PREFERRED_FUEL.sync(player);
        });
    }
}
