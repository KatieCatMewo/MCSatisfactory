package net.kittykat.mcsatisfactory.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

@Environment(EnvType.CLIENT)
@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {
    private @Shadow @Final @Mutable static IntSupplier BRAND_ARGB;
    @Shadow @Final @Mutable static Identifier LOGO;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(MinecraftClient client, ResourceReload monitor, Consumer<Optional<Throwable>> exceptionHandler,
                        boolean reloading, CallbackInfo ci) {
        BRAND_ARGB = () -> client.options.getMonochromeLogo().getValue() ? 0xff000000 : 0xff232323;
        LOGO       = new Identifier(MOD_ID, "textures/gui/title/loading_screen_logo.png");
    }

    @Redirect(method = "render", at = @At(
            value = "INVOKE:FIRST",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;blendFunc(II)V"))
    private void fixBlendFunc(int srcFactor, int dstFactor) {
        RenderSystem.defaultBlendFunc();
    }
}
