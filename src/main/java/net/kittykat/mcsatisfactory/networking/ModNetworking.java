package net.kittykat.mcsatisfactory.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;

public abstract class ModNetworking {
    public static final Identifier JETPACK_FUEL_CHANGE_PACKET  = new Identifier(MOD_ID, "jetpack_fuel_change");
    public static final Identifier JETPACK_ACTIVE_STATE_PACKET = new Identifier(MOD_ID, "jetpack_active_state");
    public static final Identifier JETPACK_BOOST_PACKET        = new Identifier(MOD_ID, "jetpack_boost");

    public static final Identifier RESOURCE_SCAN_PACKET = new Identifier(MOD_ID, "resource_scan");
    public static final Identifier SCAN_RESPONSE_PACKET = new Identifier(MOD_ID, "resource_scan_response");

    public static void register() {
        LOGGER.debug("registering packets for {}", MOD_ID);

        registerC2SPacket(JETPACK_FUEL_CHANGE_PACKET,  JetpackFuelChangeC2SPacket::new);
        registerC2SPacket(JETPACK_ACTIVE_STATE_PACKET, JetpackActiveStateC2SPacket::new);
        registerC2SPacket(JETPACK_BOOST_PACKET,        JetpackBoostC2SPacket::new);

        registerC2SPacket(RESOURCE_SCAN_PACKET, ResourceScanC2SPacket::new);
        registerS2CPacket(SCAN_RESPONSE_PACKET, ScanResponseS2CPacket::new);
    }

    private static <P extends ClientToServerPacket> void registerC2SPacket(Identifier id, PacketFactory<P> factory) {
        ServerPlayNetworking.registerGlobalReceiver(id,
                (server, player, handler, buf, responseSender) -> {
            P packet = factory.create(buf);
            packet.handle(server, player, handler, responseSender);
        });
    }
    private static <P extends ServerToClientPacket> void registerS2CPacket(Identifier id, PacketFactory<P> factory) {
        ClientPlayNetworking.registerGlobalReceiver(id,
                (client, handler, buf, responseSender) -> {
            P packet = factory.create(buf);
            packet.handle(client, handler, responseSender);
        });
    }
    @FunctionalInterface
    private interface PacketFactory<P> {
        P create(PacketByteBuf buf);
    }

    public static <P extends ClientToServerPacket> void sendFromClient(Identifier id, @NotNull P packet) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        packet.write(buf);
        ClientPlayNetworking.send(id, buf);
    }

    public static <P extends ServerToClientPacket> void sendToClient(ServerPlayerEntity player, Identifier id, @NotNull P packet) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        packet.write(buf);
        ServerPlayNetworking.send(player, id, buf);
    }
    public static <P extends ServerToClientPacket> void sendToAllClients(@NotNull MinecraftServer server,
                                                                         Identifier id, @NotNull P packet) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        packet.write(buf);
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerPlayNetworking.send(player, id, buf);
        }
    }

    public static BlockPos @NotNull [] readBlockPosArray(@NotNull PacketByteBuf buf) {
        int len = buf.readVarInt();
        BlockPos[] array = new BlockPos[len];
        for (int i = 0; i < len; i++) {
            array[i] = buf.readBlockPos();
        }
        return array;
    }
    public static void writeBlockPosArray(@NotNull PacketByteBuf buf, BlockPos @NotNull [] array) {
        buf.writeVarInt(array.length);
        for (BlockPos blockPos : array) {
            buf.writeBlockPos(blockPos);
        }
    }
}
