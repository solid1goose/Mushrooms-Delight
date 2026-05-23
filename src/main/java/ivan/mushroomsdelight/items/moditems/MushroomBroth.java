package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.effects.ModEffects;
import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class MushroomBroth extends ModItem {
    public MushroomBroth() {
        super(new Properties()
                        .stacksTo(16)
                        .food(new FoodProperties.Builder()
                                .alwaysEdible()
                                .nutrition(3)
                                .saturationModifier(0.2f)
                                .build(),
                        Consumable.builder()
                                .consumeSeconds(1)
                                .sound(SoundEvents.GENERIC_DRINK)
                                .animation(ItemUseAnimation.DRINK)
                                .hasConsumeParticles(false)
                                .onConsume(new ApplyStatusEffectsConsumeEffect(
                                        new MobEffectInstance(ModEffects.MUSHROOM_SPEED, 400, 0, false, false, false)
                                ))
                                .build())
                , "mushroom_broth");
    }
}
