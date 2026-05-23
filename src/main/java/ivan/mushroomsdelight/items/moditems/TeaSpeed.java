package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;

public class TeaSpeed extends ModItem {
    public TeaSpeed() {
        super(new Properties()
                        .stacksTo(16)
                        .food(new FoodProperties.Builder()
                                .nutrition(8)
                                .saturationModifier(0.6f)
                                .build(),
                        Consumable.builder()
                                .consumeSeconds(1)
                                .sound(SoundEvents.GENERIC_DRINK)
                                .animation(ItemUseAnimation.DRINK)
                                .hasConsumeParticles(false)
                                .build()
                                )
                , "speed_tea");
    }
}
