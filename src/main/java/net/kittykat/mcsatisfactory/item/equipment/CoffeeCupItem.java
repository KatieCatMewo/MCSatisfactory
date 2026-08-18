package net.kittykat.mcsatisfactory.item.equipment;

import net.kittykat.mcsatisfactory.advancement.ModCriteria;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CoffeeCupItem extends Item {
    public CoffeeCupItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.MAIN_HAND) {
            if (user instanceof ServerPlayerEntity player) {
                // ToDo: play drinking animation
                user.playSound(SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, .5f, 1f);
                ModCriteria.DRINK_COFFEE.trigger(player);
            }
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }
}
