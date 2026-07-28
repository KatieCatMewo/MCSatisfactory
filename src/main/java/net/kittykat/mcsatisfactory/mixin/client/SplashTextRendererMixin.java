package net.kittykat.mcsatisfactory.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SplashTextRenderer.class)
public class SplashTextRendererMixin {
    private @Shadow @Final String text;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void renderSplashes(DrawContext context, int screenWidth, TextRenderer textRenderer, int alpha, @NotNull CallbackInfo ci) {
        ci.cancel();

        String[] lines = text.split("<>");
        int textWidth = 0;
        for (String line : lines) {
            int width = textRenderer.getWidth(line);
            if (width > textWidth) {
                textWidth = width;
            }
        }
        context.getMatrices().push();
        context.getMatrices().translate((screenWidth / 2f) + 123f, 70f, 0f);
        context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-20f));
        float s = 1.8f - MathHelper.abs(MathHelper.sin((Util.getMeasuringTimeMs() % 1000L) / 1000f * MathHelper.TAU) * .1f);
        s *= (100f / (textWidth + 32));
        context.getMatrices().scale(s, s, s);

        int color = 0xffffff | alpha;
        for (int l = 0; l < lines.length; l++) {
            context.drawCenteredTextWithShadow(textRenderer, lines[l], 0, 8 * (l - 1), color);
        }
        context.getMatrices().pop();
    }
}
