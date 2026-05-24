package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class DriedCrimson extends ModItem {
    public DriedCrimson(){
        super(new Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(3)
                                .saturationModifier(0.5f)
                                .build())
                , "dried_crimson_fungus");
    }
}
