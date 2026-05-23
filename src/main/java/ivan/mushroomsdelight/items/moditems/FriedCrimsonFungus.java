package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class FriedCrimsonFungus extends ModItem {
    public FriedCrimsonFungus(){
        super(new Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(5)
                                .saturationModifier(0.7f)
                                .build())
                , "fried_crimson_fungus");
    }
}
