package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kittykat.mcsatisfactory.controls.ResourceScanner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public record ScanResponseS2CPacket(BlockPos[] scanPoints) implements ServerToClientPacket {
    public ScanResponseS2CPacket(@NotNull PacketByteBuf buf) {
        this(ModNetworking.readBlockPosArray(buf));
    }

    @Override
    public void write(@NotNull PacketByteBuf buf) {
        ModNetworking.writeBlockPosArray(buf, scanPoints);
    }

    @Override
    public void handle(@NotNull MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {
        ClientPlayerEntity player = client.player;
        if (player != null) {
            for (BlockPos pos : scanPoints) {
                ResourceScanner.INSTANCE.addScanHit(pos, player);
            }
        }
    }
}
