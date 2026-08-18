package net.kittykat.mcsatisfactory.render.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kittykat.mcsatisfactory.item.equipment.weapon.NobeliskDetonatorItem;
import net.kittykat.mcsatisfactory.item.equipment.weapon.RangedWeaponItem;
import net.kittykat.mcsatisfactory.item.equipment.weapon.RebarGunItem;
import net.kittykat.mcsatisfactory.item.equipment.weapon.RifleItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

@Environment(EnvType.CLIENT)
public abstract class CrosshairRenderer {
    private static final Identifier CROSSHAIRS = new Identifier(MOD_ID, "textures/gui/crosshairs.png");
    private static final int TEX_WIDTH  = 256;
    private static final int TEX_HEIGHT = 192;
    private static final int TEX_TILE_SIZE = 64;

    private static final int SCALE =  3;
    private static final int BLOOM_SIZE_OUTER = 10;
    private static final int BLOOM_SIZE_INNER =  1;
    private static final int SIZE  = BLOOM_SIZE_OUTER + BLOOM_SIZE_INNER + BLOOM_SIZE_OUTER;  // 10 + 1 + 10 = 21
    private static final int BLOOM_SPRITE_SIZE_OUTER = SCALE * BLOOM_SIZE_OUTER;  // 3 * 10 = 30
    private static final int BLOOM_SPRITE_SIZE_INNER = SCALE * BLOOM_SIZE_INNER;  // 3 *  1 =  3
    private static final int SPRITE_SIZE = SCALE * SIZE;  // 3 * 21 = 63

    private static final int V0 = TEX_TILE_SIZE;
    private static final int V1 = V0 + BLOOM_SPRITE_SIZE_OUTER;

    public static void renderCrosshair(DrawContext context) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            ItemStack heldStack = player.getInventory().getMainHandStack();
            Item heldItem = heldStack.getItem();

            int scaledWidth  = context.getScaledWindowWidth();
            int scaledHeight = context.getScaledWindowHeight();
            int x = (scaledWidth  - SIZE) / 2;
            int y = (scaledHeight - SIZE) / 2;

            if (heldItem instanceof RangedWeaponItem weapon) {
                int c = weapon.getCrosshairIndex(heldStack);
                int u = TEX_TILE_SIZE * c;

                if (weapon instanceof NobeliskDetonatorItem) {
                    drawNobeliskCrosshair(context, x, y);
                } else if (weapon instanceof RifleItem rifle) {
                    drawRifleCrosshair(context, x, y, u, rifle.getCrosshairBloom(c));
                } else if (weapon instanceof RebarGunItem) {
                        drawRebarGunCrosshair(context, x, y, u);
                } else {
                    drawCrosshair(context, x, y);  // fallback
                }
            } else {
                drawCrosshair(context, x, y);
            }
        }
    }

    private static void drawCrosshair(DrawContext context, int x, int y) {
        drawCrosshairPart(context, x, y, SIZE, SIZE, 0, 0, SPRITE_SIZE, SPRITE_SIZE);
    }
    private static void drawNobeliskCrosshair(@NotNull DrawContext context, int x, int y) {
        // ToDo: actually draw it
        context.drawTexture(CROSSHAIRS, x, y, SIZE, SIZE, 2 * TEX_TILE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE, TEX_WIDTH, TEX_HEIGHT);
    }
    private static void drawRifleCrosshair(DrawContext context, int x, int y, int u0, int bloom) {
        // draw rifle crosshair
        int x0 = x - bloom;
        int y0 = y - bloom;
        int x1 = x + BLOOM_SIZE_OUTER;
        int y1 = y + BLOOM_SIZE_OUTER;
        int x2 = x1 + BLOOM_SIZE_INNER + bloom;
        int y2 = y1 + BLOOM_SIZE_INNER + bloom;

        int u1 = u0 + BLOOM_SPRITE_SIZE_OUTER;
        int u2 = u1 + BLOOM_SPRITE_SIZE_INNER;
        int v2 = V1 + BLOOM_SPRITE_SIZE_INNER;

        // center
        drawCrosshairPart(context, x1, y1, BLOOM_SIZE_INNER, BLOOM_SIZE_INNER, u1, V1, BLOOM_SPRITE_SIZE_INNER, BLOOM_SPRITE_SIZE_INNER);

        // horizontal
        drawCrosshairPart(context, x1, y0, BLOOM_SIZE_INNER, BLOOM_SIZE_OUTER, u1, V0, BLOOM_SPRITE_SIZE_INNER, BLOOM_SPRITE_SIZE_OUTER);
        drawCrosshairPart(context, x1, y2, BLOOM_SIZE_INNER, BLOOM_SIZE_OUTER, u1, v2, BLOOM_SPRITE_SIZE_INNER, BLOOM_SPRITE_SIZE_OUTER);
        // vertical
        drawCrosshairPart(context, x0, y1, BLOOM_SIZE_OUTER, BLOOM_SIZE_INNER, u0, V1, BLOOM_SPRITE_SIZE_OUTER, BLOOM_SPRITE_SIZE_INNER);
        drawCrosshairPart(context, x2, y1, BLOOM_SIZE_OUTER, BLOOM_SIZE_INNER, u2, V1, BLOOM_SPRITE_SIZE_OUTER, BLOOM_SPRITE_SIZE_INNER);

        // diagonal
        drawCrosshairPart(context, x0, y0, BLOOM_SIZE_OUTER, BLOOM_SIZE_OUTER, u0, V0, BLOOM_SPRITE_SIZE_OUTER, BLOOM_SPRITE_SIZE_OUTER);
        drawCrosshairPart(context, x2, y0, BLOOM_SIZE_OUTER, BLOOM_SIZE_OUTER, u2, V0, BLOOM_SPRITE_SIZE_OUTER, BLOOM_SPRITE_SIZE_OUTER);
        drawCrosshairPart(context, x0, y2, BLOOM_SIZE_OUTER, BLOOM_SIZE_OUTER, u0, v2, BLOOM_SPRITE_SIZE_OUTER, BLOOM_SPRITE_SIZE_OUTER);
        drawCrosshairPart(context, x2, y2, BLOOM_SIZE_OUTER, BLOOM_SIZE_OUTER, u2, v2, BLOOM_SPRITE_SIZE_OUTER, BLOOM_SPRITE_SIZE_OUTER);
    }
    private static void drawRebarGunCrosshair(DrawContext context, int x, int y, int u) {
        drawCrosshairPart(context, x, y, SIZE, SIZE, u, 2 * TEX_TILE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
    }

    private static void drawCrosshairPart(@NotNull DrawContext context, int x, int y, int width, int height,
                                          int u, int v, int regionWidth, int regionHeight) {
        context.drawTexture(CROSSHAIRS, x, y, width, height, u, v, regionWidth, regionHeight, TEX_WIDTH, TEX_HEIGHT);
    }
}
