package ivan.mushroomsdelight.items.moditems;


import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class BrownPieces extends ModItem {
    public BrownPieces(){
        super(new Properties()
                        .stacksTo(64)
                , "brown_mushroom_pieces");
    }
}
