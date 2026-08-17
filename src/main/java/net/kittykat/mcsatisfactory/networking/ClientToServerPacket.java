package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public interface ClientToServerPacket extends Packet<ServerPlayPacketListener> {
    void handle(@NotNull MinecraftServer server, ServerPlayerEntity player,
                ServerPlayNetworkHandler handler, PacketSender responseSender);

    @Override
    default void apply(ServerPlayPacketListener listener) {}
}
