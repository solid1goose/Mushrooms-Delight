package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class FriedBrown extends ModItem {
    public FriedBrown(){
        super(new Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(3)
                                .saturationModifier(0.7f)
                                .build())
                , "fried_brown_mushroom");
    }
}
