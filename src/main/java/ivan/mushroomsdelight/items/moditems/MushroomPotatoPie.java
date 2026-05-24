package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class MushroomPotatoPie extends ModItem {
    public MushroomPotatoPie() {
        super(new Properties()
                        .stacksTo(16)
                        .food(new FoodProperties.Builder()
                                .nutrition(8)
                                .saturationModifier(0.6f)
                                .build())
                , "mushroom_potato_pie");
    }
}
