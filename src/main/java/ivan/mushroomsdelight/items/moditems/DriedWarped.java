package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class DriedWarped extends ModItem {
    public DriedWarped(){
        super(new Item.Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(3)
                                .saturationModifier(0.5f)
                                .build())
                , "dried_warped_fungus");
    }
}
