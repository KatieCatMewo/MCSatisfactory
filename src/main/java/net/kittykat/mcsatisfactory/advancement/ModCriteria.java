package net.kittykat.mcsatisfactory.advancement;

import net.minecraft.advancement.criterion.Criteria;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;
import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;

public abstract class ModCriteria {
    public static final DrinkCoffeeCriterion DRINK_COFFEE = Criteria.register(new DrinkCoffeeCriterion());

    public static void register() {
        LOGGER.debug("registering advancement criteria for {}", MOD_ID);
    }
}
