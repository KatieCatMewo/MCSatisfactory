package net.kittykat.mcsatisfactory.render;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.kittykat.mcsatisfactory.controls.ResourceScanner;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import org.jetbrains.annotations.NotNull;

public class ScannerPulseRenderer {
    public  static final ScannerPulseRenderer INSTANCE = new ScannerPulseRenderer();
    private static final float OFFSET    = 2e-3f;
    private static final int   RGB       = 0x00eeff;

    public  boolean  enabled   = false;
    public  BlockPos centerPos = BlockPos.ORIGIN;
    public  float    radius    = 0f;
    private int      alpha     = 255;

    public void render(@NotNull WorldRenderContext context, MinecraftClient client) {
        if (enabled) {
            if (!client.isPaused()) {
                radius += ResourceScanner.SCAN_TRAVEL_SPEED * client.getLastFrameDuration();
                alpha   = getAlpha(radius, client.options.getClampedViewDistance());
            }
            if (alpha > 0) {
                MatrixStack matrices = context.matrixStack();
                Vec3d cameraPos = context.camera().getPos();

                double x = centerPos.getX() - cameraPos.x;
                double y = centerPos.getY() - cameraPos.y;
                double z = centerPos.getZ() - cameraPos.z;
                matrices.push();
                matrices.translate(x, y, z);

                VertexConsumerProvider consumers = context.consumers();
                ClientWorld world = context.world();
                if ((consumers != null) && (world != null)) {
                    VertexConsumer buffer = consumers.getBuffer(ModRenderLayer.SCANNER_HIGHLIGHT);
                    draw(matrices, buffer, world, (int) radius);
                }
                matrices.pop();
            }
        }
    }
    private static int getAlpha(float distance, int renderDistance) {
        float t = distance / (16f * renderDistance);
        t *= t;  // ^2
        t *= t;  // ^4
        t *= t;  // ^8
        return (int) (255f * (1f - t));
    }

    private void draw(MatrixStack matrices, VertexConsumer buffer, ClientWorld world, int r) {
        int x = r;
        int z = 0;
        int p = 1 - r;
        while (x > z) {
            z++;
            if (p <= 0) {
                p += ((2 * z) + 1);
            } else {
                x--;
                p += (2 * (z - x) + 1);
            }
            if (x < z)
                break;

            drawCopies(matrices, buffer, world, x, z);
            if (x != z) {
                drawCopies(matrices, buffer, world, z, x);
            }
        }
        drawPos(matrices, buffer, world,    r, 0);
        drawPos(matrices, buffer, world,   -r, 0);
        drawPos(matrices, buffer, world, 0,    r);
        drawPos(matrices, buffer, world, 0,   -r);
    }
    private void drawCopies(MatrixStack matrices, VertexConsumer buffer, ClientWorld world, int u, int v) {
        drawPos(matrices, buffer, world,  u,  v);
        drawPos(matrices, buffer, world,  u, -v);
        drawPos(matrices, buffer, world, -u,  v);
        drawPos(matrices, buffer, world, -u, -v);
    }
    private void drawPos(MatrixStack matrices, VertexConsumer buffer, @NotNull ClientWorld world, int x, int z) {
        BlockPos pos = new BlockPos(centerPos.getX() + x, 0, centerPos.getZ() + z);
        int wx = pos.getX();
        int wz = pos.getZ();
        int topY;
        if (true) {  // ToDo: add options check
            // Fast
            topY = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, wx, wz) - 1;
            checkCube(matrices, buffer, world, pos.add(0, topY, 0), x, topY - centerPos.getY(), z);
        } else {
            // Fancy
            topY = world.getTopY(Heightmap.Type.WORLD_SURFACE, wx, wz);
            for (int y = world.getBottomY(); y <= topY; y++) {
                checkCube(matrices, buffer, world, pos.add(0, y, 0), x, y - centerPos.getY(), z);
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void checkCube(@NotNull MatrixStack matrices, VertexConsumer buffer,
                           @NotNull ClientWorld world, BlockPos pos, int dx, int dy, int dz) {
        MatrixStack.Entry entry = matrices.peek();

        BlockState state = world.getBlockState(pos);
        if (state.isAir() || state.isLiquid()) {
            return;
        }
        int x2 = dx + 1;
        int y2 = dy + 1;
        int z2 = dz + 1;

        if (shouldDrawFace(world, pos, Direction.UP)) { // Top Face
            vertex(entry, buffer, dx, y2 + OFFSET, dz);
            vertex(entry, buffer, dx, y2 + OFFSET, z2);
            vertex(entry, buffer, x2, y2 + OFFSET, z2);
            vertex(entry, buffer, x2, y2 + OFFSET, dz);
        }
        if (shouldDrawFace(world, pos, Direction.DOWN)) { // Bottom Face
            vertex(entry, buffer, dx, dy - OFFSET, dz);
            vertex(entry, buffer, x2, dy - OFFSET, dz);
            vertex(entry, buffer, x2, dy - OFFSET, z2);
            vertex(entry, buffer, dx, dy - OFFSET, z2);
        }
        if (shouldDrawFace(world, pos, Direction.NORTH)) { // North Face
            vertex(entry, buffer, dx, dy, dz - OFFSET);
            vertex(entry, buffer, dx, y2, dz - OFFSET);
            vertex(entry, buffer, x2, y2, dz - OFFSET);
            vertex(entry, buffer, x2, dy, dz - OFFSET);
        }
        if (shouldDrawFace(world, pos, Direction.SOUTH)) { // South Face
            vertex(entry, buffer, dx, dy, z2 + OFFSET);
            vertex(entry, buffer, x2, dy, z2 + OFFSET);
            vertex(entry, buffer, x2, y2, z2 + OFFSET);
            vertex(entry, buffer, dx, y2, z2 + OFFSET);
        }
        if (shouldDrawFace(world, pos, Direction.EAST)) { // East Face
            vertex(entry, buffer, x2 + OFFSET, dy, dz);
            vertex(entry, buffer, x2 + OFFSET, y2, dz);
            vertex(entry, buffer, x2 + OFFSET, y2, z2);
            vertex(entry, buffer, x2 + OFFSET, dy, z2);
        }
        if (shouldDrawFace(world, pos, Direction.WEST)) { // West Face
            vertex(entry, buffer, dx - OFFSET, dy, dz);
            vertex(entry, buffer, dx - OFFSET, dy, z2);
            vertex(entry, buffer, dx - OFFSET, y2, z2);
            vertex(entry, buffer, dx - OFFSET, y2, dz);
        }
    }
    private void vertex(MatrixStack.@NotNull Entry entry, @NotNull VertexConsumer buffer, float x, float y, float z) {
        buffer.vertex(entry.getPositionMatrix(), x, y, z).color((alpha << 24) | RGB).next();
    }

    private static boolean shouldDrawFace(@NotNull ClientWorld world, @NotNull BlockPos pos, Direction dir) {
        return !world.getBlockState(pos.offset(dir)).isSideSolidFullSquare(world, pos, dir.getOpposite());
    }
}
