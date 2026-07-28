package net.kittykat.mcsatisfactory.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kittykat.mcsatisfactory.item.RangedWeaponItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique private static final Identifier CROSSHAIRS = new Identifier(MOD_ID, "textures/gui/crosshairs.png");
    @Unique private static final int SIZE  = 21;
    @Unique private static final int SCALE =  3;
    @Unique private static final int SPRITE_SIZE = SCALE * SIZE;

    @Redirect(method = "renderCrosshair", at = @At(
            value   = "INVOKE:FIRST",
            target  = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void renderSatisfactoryCrosshair(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            Item heldItem = player.getInventory().getMainHandStack().getItem();

            int i = 0;
            if (heldItem instanceof RangedWeaponItem weapon) i = weapon.getCrosshairIndex(player);
            u = (i & 0b11);
            v = (i >> 2);

            int scaledWidth  = context.getScaledWindowWidth();
            int scaledHeight = context.getScaledWindowHeight();
            x = (scaledWidth  - SIZE) / 2;
            y = (scaledHeight - SIZE) / 2;

            context.drawTexture(CROSSHAIRS, x, y, SIZE, SIZE, u * 64, v * 64,
                                SPRITE_SIZE, SPRITE_SIZE, 256, 192);
        }
    }
}
