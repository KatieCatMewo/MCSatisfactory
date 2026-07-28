package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;

public interface ServerToClientPacket extends Packet<ClientPlayPacketListener> {
    void handle(@NotNull MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender);

    @Override
    default void apply(ClientPlayPacketListener listener) {}
}
