package net.kittykat.mcsatisfactory.tools;

import net.kittykat.mcsatisfactory.controls.KeyHandler;
import net.kittykat.mcsatisfactory.networking.ModNetworking;
import net.kittykat.mcsatisfactory.networking.scanner.ResourceScanC2SPacket;
import net.kittykat.mcsatisfactory.render.world.ScannerPulseRenderer;
import net.kittykat.mcsatisfactory.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ResourceScanner implements KeyHandler {
    public  static final ResourceScanner INSTANCE = new ResourceScanner();
    private static final int   FIRE_DELAY        = 15;
    public  static final float SCAN_TRAVEL_SPEED = 5f;  // 100 m/s
    private static final int   MAX_DISTANCE      = 2048;

    private final ArrayList<ScannerPos> scanPoints = new ArrayList<>();
    private int     resourceIndex = 0;
    private int     delayCounter  = FIRE_DELAY;
    private boolean lastEmpty     = true;
    private boolean scanStarted   = false;
    private float   distance;

    public void tick(MinecraftClient client) {
        ScannerPulseRenderer renderer = ScannerPulseRenderer.INSTANCE;

        boolean empty = scanPoints.isEmpty();
        if (!empty) {
            if (lastEmpty) {
                scanStarted = true;
                ClientPlayerEntity player = client.player;
                if (player != null) {
                    renderer.centerPos = player.getBlockPos();
                    renderer.enabled = true;
                }
            }
            int i = 0;
            while(i < scanPoints.size()) {
                if (scanPoints.get(i).check(client)) {
                    scanPoints.remove(i);
                } else {
                    i++;
                }
            }
        }
        lastEmpty = empty;
        if (scanStarted) {
            if (!client.isPaused()) {
                distance += SCAN_TRAVEL_SPEED;
            }
            //renderer.radius = distance;
            if (distance > MAX_DISTANCE) {
                stop();
            }
        }

        if (delayCounter < FIRE_DELAY) {
            delayCounter++;
            if (delayCounter == FIRE_DELAY) {
                scan(client);
            }
        }
    }

    private void scan(@NotNull MinecraftClient client) {
        ModSounds.playUiSound(ModSounds.RESOURCE_SCAN_FIRE, client.getSoundManager(), SoundCategory.MASTER);

        ClientPlayerEntity player = client.player;
        if (player != null) {
            ModNetworking.sendFromClient(ModNetworking.RESOURCE_SCAN_PACKET, new ResourceScanC2SPacket(resourceIndex));
        }
    }
    private void startScan(@NotNull MinecraftClient client) {
        SoundManager soundManager = client.getSoundManager();

        ModSounds.stopSounds( ModSounds.RESOURCE_SCAN_START, soundManager, SoundCategory.MASTER);
        ModSounds.playUiSound(ModSounds.RESOURCE_SCAN_START, soundManager, SoundCategory.MASTER);

        stop();
        delayCounter = 0;
    }
    public void stop() {
        ScannerPulseRenderer renderer = ScannerPulseRenderer.INSTANCE;

        delayCounter = FIRE_DELAY;
        scanStarted = false;
        distance = 0f;
        renderer.radius  = 0f;
        renderer.enabled = false;
        scanPoints.clear();
    }

    public void addScanHit(BlockPos pos, @NotNull PlayerEntity player) {
        scanPoints.add(new ScannerPos(pos, player));
    }

    @Override
    public void shortPress(MinecraftClient client) {
        startScan(client);
    }

    @Override
    public void longPress(MinecraftClient client) {
        // ToDo: open selection wheel
    }
    @Override
    public void longReleased(MinecraftClient client) {
        // ToDo: close selection wheel
        startScan(client);
    }

    private class ScannerPos {
        private final int    x, y, z;
        private final double d;

        private ScannerPos(@NotNull BlockPos pos, @NotNull PlayerEntity player) {
            x = pos.getX();
            y = pos.getY();
            z = pos.getZ();
            d = Math.sqrt(player.squaredDistanceTo(x, player.getY(), z));
        }

        private boolean check(MinecraftClient client) {
            if (d <= distance) {
                ClientWorld world = client.world;
                if (world != null) {
                    world.playSound(x, y, z, ModSounds.RESOURCE_SCAN_HIT, SoundCategory.MASTER, 1f, 1f, false);
                    // ToDo: replace with actual ping visual
                    world.addParticle(ParticleTypes.SONIC_BOOM, x, y, z, 0d, 0d, 0d);
                }
                return true;
            }
            return false;
        }
    }
}
