package net.kittykat.mcsatisfactory.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.Window;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.kittykat.mcsatisfactory.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class SatisfactoryHUD implements HudRenderCallback {
    private static final int   SLOT_BG_COLOR = 0x80232323;
    private static final short ITEM_SIZE = 16;
    private static final short SLOT_SIZE = 22;
    private static final short SLOT_Y    = 25;

    private static final short[] SLOT_X_POSITIONS = {110, -1, -1, 160, 135, 185};
    private static final HashMap<Item, SlotDrawMethod> CHEST_SLOT_METHOD_MAP = new HashMap<>();
    @SuppressWarnings("unchecked")
    private static final HashMap<Item, SlotDrawMethod>[] SLOT_METHOD_MAPS =
            new HashMap[]{null, null, null, null, CHEST_SLOT_METHOD_MAP, null};

    private float lastDT;

    static {
        CHEST_SLOT_METHOD_MAP.put(ModItems.JETPACK, SatisfactoryHUD::drawJetpackHUD);
    }

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        float deltaTick = tickDelta - lastDT;

        MinecraftClient client = MinecraftClient.getInstance();
        GameOptions options = client.options;
        Window window = client.getWindow();

        //int screenWidth  = window.getScaledWidth();
        int screenHeight = window.getScaledHeight();

        if (!options.debugTpsEnabled) {
            int slotY = screenHeight - SLOT_Y;
            for (int s = 0; s < SLOT_X_POSITIONS.length; s++) {
                drawEquipmentSlot(context, client, deltaTick, slotY, s);
            }
        }

        lastDT = deltaTick;
    }

    private static void drawEquipmentSlot(DrawContext context, MinecraftClient client, float deltaTick, int y, int slot) {
        if (SLOT_X_POSITIONS[slot] < 0) return;

        ClientPlayerEntity player = client.player;
        if (player != null) {
            ItemStack stack = player.getEquippedStack(EquipmentSlot.values()[slot]);
            Item item = stack.getItem();

            if (!stack.isEmpty()) {
                drawItemSlotPreview(context, client, SLOT_X_POSITIONS[slot], y, stack);
            }

            HashMap<Item, SlotDrawMethod> slotMethodMap = SLOT_METHOD_MAPS[slot];
            if ((slotMethodMap != null) && (slotMethodMap.containsKey(item))) {
                slotMethodMap.get(item).draw(context, client, deltaTick, y);
            }
        }
    }
    private static void drawItemSlotPreview(@NotNull DrawContext context, @NotNull MinecraftClient client,
                                            int x, int y, ItemStack stack) {
        context.fill(x, y, x + SLOT_SIZE, y + SLOT_SIZE, SLOT_BG_COLOR);
        int posOffset = (SLOT_SIZE - ITEM_SIZE) / 2;
        int itemX = x + posOffset;
        int itemY = y + posOffset;

        context.drawItem(stack, itemX, itemY);
        context.drawItemInSlot(client.inGameHud.getTextRenderer(), stack, itemX, itemY);
    }

    private static void drawJetpackHUD(DrawContext context, MinecraftClient client, float deltaTick, int y) {

    }

    @FunctionalInterface
    private interface SlotDrawMethod {
        void draw(DrawContext context, MinecraftClient client, float deltaTick, int y);
    }
}
