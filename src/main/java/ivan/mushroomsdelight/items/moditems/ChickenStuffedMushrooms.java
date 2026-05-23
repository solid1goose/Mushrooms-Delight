package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.world.food.FoodProperties;

public class ChickenStuffedMushrooms extends ModItem {
    public ChickenStuffedMushrooms() {
        super(new Properties()
                        .food(new FoodProperties(10, 0.5F, false)),
                "chicken_stuffed_mushrooms");
    }
}
