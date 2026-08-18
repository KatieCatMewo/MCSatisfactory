package net.kittykat.mcsatisfactory.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.kittykat.mcsatisfactory.advancement.DrinkCoffeeCriterion;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Item;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static net.minecraft.advancement.AdvancementFrame.*;
import static net.minecraft.item.Items.*;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.item.ModItems.*;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    private Consumer<Advancement> exporter;

    protected ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        exporter = consumer;
        generateAdvancements();
    }

    private void generateAdvancements() {
        Advancement root = addAdvancement("root", XENO_ZAPPER, TASK, false, null,
                "impossible", new ImpossibleCriterion.Conditions());

        Advancement drinkCoffee = addAdvancement("drink_coffee", COFFEE_CUP, TASK, true, root,
                "drink_coffee", DrinkCoffeeCriterion.any());

        Advancement peakHeight = addAdvancement("peak_height", SNOWBALL, GOAL, true, root,
                "peak_height", TickCriterion.Conditions.createLocation(LocationPredicate.y(NumberRange.FloatRange.atLeast(256))));
        Advancement maxHeight = addAdvancement("max_height", LADDER, CHALLENGE, true, peakHeight,
                "max_height", TickCriterion.Conditions.createLocation(LocationPredicate.y(NumberRange.FloatRange.atLeast(320))));
    }

    private Advancement addAdvancement(@NotNull String id, Item icon, AdvancementFrame frame, boolean show,
                                       @Nullable Advancement parent, String criterionName, AdvancementCriterion criterion) {
        return Advancement.Builder.create()
                .display(icon,
                        translatable("%s.title".formatted(id)), translatable("%s.description".formatted(id)),
                        id.equals("root") ? Identifier.of(MOD_ID, "textures/gui/advancements/backgrounds/factory.png") : null,
                        frame,
                        show, show, false
                )
                .parent(parent)
                .criterion(criterionName, criterion).build(exporter, "%s:%s".formatted(MOD_ID, id));
    }
    private Advancement addAdvancement(String id, Item icon, AdvancementFrame frame, boolean show,
                                       Advancement parent, String criterionName, CriterionConditions conditions) {
        return addAdvancement(id, icon, frame, show, parent, criterionName, new AdvancementCriterion(conditions));
    }

    @Contract("_ -> new")
    private static @NotNull Text translatable(String id) {
        return Text.translatable("advancement.%s.%s".formatted(MOD_ID, id));
    }
}
