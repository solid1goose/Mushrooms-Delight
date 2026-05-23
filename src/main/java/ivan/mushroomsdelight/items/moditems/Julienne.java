package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class Julienne extends ModItem {
    public Julienne() {
        super(new Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(8)
                                .saturationModifier(0.6f)
                                .build())
                , "julienne");
    }
}
