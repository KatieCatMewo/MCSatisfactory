package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
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
            System.out.println(player.getInventory().armor);
            //JetpackDataComponent data = ModComponents.JETPACK_DATA.get(player.getInventory().getArmorStack(2));
            //data.updateActive(activeState);
        });
    }
}
