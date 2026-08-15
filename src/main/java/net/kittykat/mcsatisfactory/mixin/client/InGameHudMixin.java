package net.kittykat.mcsatisfactory.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kittykat.mcsatisfactory.render.hud.CrosshairRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Redirect(method = "renderCrosshair", at = @At(
            value   = "INVOKE:FIRST",
            target  = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void renderSatisfactoryCrosshair(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        CrosshairRenderer.renderCrosshair(context);
    }
}
