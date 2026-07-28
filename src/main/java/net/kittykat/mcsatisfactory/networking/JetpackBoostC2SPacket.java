package net.kittykat.mcsatisfactory.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kittykat.mcsatisfactory.events.ModEvents;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.kittykat.mcsatisfactory.particle.ModParticles;
import org.jetbrains.annotations.NotNull;

public record JetpackBoostC2SPacket(boolean removeFuel) implements ClientToServerPacket {
    public JetpackBoostC2SPacket(@NotNull PacketByteBuf buf) {
        this(buf.readBoolean());
    }

    @Override
    public void write(@NotNull PacketByteBuf buf) {
        buf.writeBoolean(removeFuel);
    }

    @Override
    public void handle(@NotNull MinecraftServer server, ServerPlayerEntity player,
                       ServerPlayNetworkHandler handler, PacketSender responseSender) {
        server.execute(() -> {
            if (removeFuel) {
                // TODO: decrement remaining fuel ticks
            }

            player.emitGameEvent(ModEvents.JETPACK_FLY);
            ServerWorld world = player.getServerWorld();
            float rad = (player.getBodyYaw() + 90f) * MathHelper.RADIANS_PER_DEGREE;
            world.spawnParticles(ModParticles.JETPACK_FLAME_EMITTER,
                    player.getX() - .3d * MathHelper.cos(rad),
                    player.getY() + 1d,
                    player.getZ() - .3d * MathHelper.sin(rad),
                    1, 0d, 0d, 0d, 0d);
        });
    }
}
