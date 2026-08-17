package net.kittykat.mcsatisfactory.item;

import net.kittykat.mcsatisfactory.components.entity.PreferredFuelComponent;
import net.kittykat.mcsatisfactory.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.kittykat.mcsatisfactory.components.ModComponents;
import net.kittykat.mcsatisfactory.components.item.JetpackDataComponent;
import net.kittykat.mcsatisfactory.networking.JetpackActiveStateC2SPacket;
import net.kittykat.mcsatisfactory.networking.JetpackBoostC2SPacket;
import net.kittykat.mcsatisfactory.networking.JetpackFuelChangeC2SPacket;
import net.kittykat.mcsatisfactory.networking.ModNetworking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public class JetpackItem extends ArmorItem {
    private static final String TOOLTIP_KEY = "tooltip.%s.change_fuel".formatted(MOD_ID);
    private static final double MAX_ACCELERATION = .3d;

    public JetpackItem(@NotNull Settings settings) {
        super(ArmorMaterials.IRON, Type.CHESTPLATE, settings.maxCount(1).maxDamage(0).rarity(Rarity.UNCOMMON));
    }

    @Override
    public SoundEvent getEquipSound() {
        return ModSounds.SLOT_BACK_EQUIP;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            PreferredFuelComponent data = ModComponents.PREFERRED_FUEL.get(player);
            tooltip.add(Text.literal("-> ").append(Text.translatable(data.getPreferredFuelItem().getTranslationKey())));
            tooltip.add(Text.translatable(TOOLTIP_KEY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType,
                             @NotNull PlayerEntity player, StackReference cursorStackReference) {
        if ((clickType == ClickType.RIGHT) && otherStack.isEmpty()) {
            if (player.getWorld().isClient) {
                JetpackFuelChangeC2SPacket packet = new JetpackFuelChangeC2SPacket();
                ModNetworking.sendFromClient(ModNetworking.JETPACK_FUEL_CHANGE_PACKET, packet);
            }
            return true;
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            JetpackDataComponent itemData   = ModComponents.JETPACK_DATA.get(stack);
            PreferredFuelComponent fuelData = ModComponents.PREFERRED_FUEL.get(player);

            if (player.getEquippedStack(EquipmentSlot.CHEST) == stack) {
                boolean validPlayerState = isValidPlayerState(player);
                boolean grounded = isPlayerGrounded(player);
                if (world.isClient) { // Client
                    boolean lastActive = itemData.isActive;
                    itemData.isActive = false;

                    if (validPlayerState && !grounded) {  // ToDo: && (itemData.remainingFuel > 0)
                        itemData.isActive = ((ClientPlayerEntity) player).input.jumping;
                        if (itemData.isActive) {
                            double velocityY = player.getVelocity().getY();
                            double targetVelocity = fuelData.getTargetVelocity();
                            double velDifference = targetVelocity - velocityY;
                            boolean removeFuel = (velDifference > 0d);
                            if (removeFuel) {
                                double addedVelocity = MathHelper.clamp(velDifference, 0d, MAX_ACCELERATION);
                                player.addVelocity(0d, addedVelocity, 0d);
                                player.velocityModified = true;
                                if (player.getVelocity().y >= 0d) {
                                    player.fallDistance = 0f;
                                }
                            }
                            JetpackBoostC2SPacket packet = new JetpackBoostC2SPacket(removeFuel);
                            ModNetworking.sendFromClient(ModNetworking.JETPACK_BOOST_PACKET, packet);
                        }
                    }
                    if (!lastActive && itemData.isActive) {
                        changeActiveState(true);
                    } else if (lastActive && !itemData.isActive) {
                        changeActiveState(false);
                    }
                } else { // Server
                    if (validPlayerState && grounded) {
                        // TODO: refuel jetpack
                    }

                    if (itemData.queueActivate) {
                        // TODO: play activate sound, start sound loop
                        itemData.queueActivate = false;
                    } else if (itemData.queueDeactivate) {
                        // TODO: play deactivate sound, cancel sound loop
                        itemData.queueDeactivate = false;
                    }
                }
            }
        }
    }

    private static void changeActiveState(boolean active) {
        JetpackActiveStateC2SPacket packet = new JetpackActiveStateC2SPacket(active);
        ModNetworking.sendFromClient(ModNetworking.JETPACK_ACTIVE_STATE_PACKET, packet);
    }

    private static boolean isValidPlayerState(@NotNull PlayerEntity player) {
        return (!player.isSubmergedInWater() && !player.isInLava());
    }
    private static boolean isPlayerGrounded(@NotNull PlayerEntity player) {
        return (player.isOnGround() || player.isClimbing() || player.isSpectator());
    }

    private static ItemStack getFuelStack(@NotNull PlayerInventory inventory, Item preferredFuel) {
        int count = 0;
        Item fuelItem = null;
        for (int s = 0; s < inventory.main.size(); s++) {
            ItemStack stack = inventory.main.get(s);
            if (!stack.isEmpty()) {
                Item item = stack.getItem();
                if ((fuelItem == null) && PreferredFuelComponent.isFuel(item)) {
                    fuelItem = item;
                } else if (preferredFuel.equals(item)) {
                    count = 0;
                    fuelItem = preferredFuel;
                }
                if (item.equals(fuelItem)) {
                    count += stack.getCount();
                }
            }
        }
        if ((fuelItem == null) || (count <= 0)) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(fuelItem, count);
    }
}
