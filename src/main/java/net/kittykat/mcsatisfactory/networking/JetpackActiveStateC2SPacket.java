package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.kittykat.mcsatisfactory.components.JetpackDataComponent;
import net.kittykat.mcsatisfactory.components.ModComponents;
import org.jetbrains.annotations.NotNull;

public record JetpackActiveStateC2SPacket(boolean activeState) implements ClientToServerPacket {
    public JetpackActiveStateC2SPacket(@NotNull PacketByteBuf buf) {
        this(buf.readBoolean());
    }

    @Override
    public void write(@NotNull PacketByteBuf buf) {
        buf.writeBoolean(activeState);
    }

    @Override
    public void handle(@NotNull MinecraftServer server, ServerPlayerEntity player,
                       ServerPlayNetworkHandler handler, PacketSender responseSender) {
        server.execute(() -> {
            JetpackDataComponent data = ModComponents.JETPACK_DATA.get(player);
            data.updateActive(activeState);
        });
    }
}
