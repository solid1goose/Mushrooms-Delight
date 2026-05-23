package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class DriedRedPieces extends ModItem {
    public DriedRedPieces(){
        super(new Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(2)
                                .saturationModifier(0.5f)
                                .build())
                , "dried_red_mushroom_pieces");
    }
}
