package net.kittykat.mcsatisfactory.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public class DrinkCoffeeCriterion extends AbstractCriterion<DrinkCoffeeCriterion.Conditions> {
    private static final Identifier ID = new Identifier(MOD_ID, "drink_coffee");

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, LootContextPredicate playerPredicate,
                                            AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new DrinkCoffeeCriterion.Conditions(playerPredicate);
    }

    public void trigger(ServerPlayerEntity player) {
        trigger(player, conditions -> true);
    }

    @NotNull
    @Contract(" -> new")
    public static DrinkCoffeeCriterion.Conditions any() {
        return new DrinkCoffeeCriterion.Conditions(LootContextPredicate.EMPTY);
    }

    public static class Conditions extends AbstractCriterionConditions {
        public Conditions(LootContextPredicate entity) {
            super(ID, entity);
        }
    }
}