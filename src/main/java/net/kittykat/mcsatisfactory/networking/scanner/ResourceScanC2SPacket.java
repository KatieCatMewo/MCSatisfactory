package net.kittykat.mcsatisfactory.networking.scanner;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kittykat.mcsatisfactory.networking.ClientToServerPacket;
import net.kittykat.mcsatisfactory.networking.ModNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public record ResourceScanC2SPacket(int resourceIndex) implements ClientToServerPacket {
    public ResourceScanC2SPacket(@NotNull PacketByteBuf buf) {
        this(buf.readByte());
    }

    @Override
    public void write(@NotNull PacketByteBuf buf) {
        buf.writeByte(resourceIndex);
    }

    @Override
    public void handle(@NotNull MinecraftServer server, ServerPlayerEntity player,
                       ServerPlayNetworkHandler handler, PacketSender responseSender) {
        // ToDo: actually scan for resources
        BlockPos[] resourcePositions = new BlockPos[5];
        resourcePositions[0] = new BlockPos(  0, 125,    0);
        resourcePositions[1] = new BlockPos(170, 180,   95);
        resourcePositions[2] = new BlockPos( 10, 120,  160);
        resourcePositions[3] = new BlockPos( 80, 110, -120);
        resourcePositions[4] = new BlockPos(-90, 120,  -75);

        ModNetworking.sendToClient(player, ModNetworking.SCAN_RESPONSE_PACKET, new ScanResponseS2CPacket(resourcePositions));
    }
}
