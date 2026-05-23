package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class SpiderEyesSoupItem extends ModItem {
    public SpiderEyesSoupItem() {
        super(new Properties()
                .stacksTo(16)
                .food(new FoodProperties.Builder()
                        .nutrition(8)
                        .saturationModifier(0.6f)
                        .build())
        , "spider_eye_soup");
    }
}