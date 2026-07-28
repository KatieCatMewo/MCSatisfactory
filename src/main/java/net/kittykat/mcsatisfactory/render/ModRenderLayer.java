package net.kittykat.mcsatisfactory.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public abstract class ModRenderLayer extends RenderLayer {
    public static final RenderLayer SCANNER_HIGHLIGHT = RenderLayer.of(
            "scanner_highlight",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS,
            0x20000,
            false, true,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(COLOR_PROGRAM).transparency(TRANSLUCENT_TRANSPARENCY).cull(ENABLE_CULLING).build(false)
    );

    public ModRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static void register() {
        LOGGER.debug("registering render layers for {}", MOD_ID);
    }
}
