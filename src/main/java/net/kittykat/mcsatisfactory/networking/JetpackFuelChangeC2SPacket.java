package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.kittykat.mcsatisfactory.components.ModComponents;
import net.kittykat.mcsatisfactory.components.JetpackDataComponent;
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
            JetpackDataComponent data = ModComponents.JETPACK_DATA.get((player));
            data.nextPreferredFuel();
            ModComponents.JETPACK_DATA.sync(player);
        });
    }
}
