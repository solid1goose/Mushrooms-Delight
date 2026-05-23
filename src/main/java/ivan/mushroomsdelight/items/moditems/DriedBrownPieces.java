package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class DriedBrownPieces extends ModItem {
    public DriedBrownPieces(){
        super(new Properties()
                        .stacksTo(64)
                        .food(new FoodProperties.Builder()
                                .nutrition(2)
                                .saturationModifier(0.5f)
                                .build())
                , "dried_brown_mushroom_pieces");
    }
}
