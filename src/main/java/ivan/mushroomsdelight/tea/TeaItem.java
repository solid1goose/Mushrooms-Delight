package ivan.mushroomsdelight.tea;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TeaItem extends ModItem {
    public int DURATION_MODIFICATOR = 0;
    public int POWER_MODIFICATOR = 0;

    public TeaItem(String name, int durationModificatorSec, int powerModificator) {
        super(new Properties()
                        .stacksTo(16)
                        .food(new FoodProperties.Builder()
                                        .alwaysEdible()
                                        .nutrition(8)
                                        .saturationModifier(0.6f)
                                        .build()
                                        ,
                                Consumable.builder()
                                        .consumeSeconds(1)
                                        .sound(SoundEvents.GENERIC_DRINK)
                                        .animation(ItemUseAnimation.DRINK)
                                        .hasConsumeParticles(false)
                                        .build()),
                name);

        this.POWER_MODIFICATOR = powerModificator;
        this.DURATION_MODIFICATOR = durationModificatorSec * 20;
    }

    @Override
    public @NonNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            BrewEffects effects = stack.get(TeaComponent.BREW_EFFECTS);
            if (effects != null) {
                effects.first().ifPresent(e -> entity.addEffect(
                        new MobEffectInstance(e, 100 + DURATION_MODIFICATOR, POWER_MODIFICATOR)));
                effects.second().ifPresent(e -> entity.addEffect(
                        new MobEffectInstance(e, 100 + DURATION_MODIFICATOR, POWER_MODIFICATOR)));
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    public static ItemLore buildLore(ItemStack stack) {
        List<Component> lines = new ArrayList<>();
        BrewEffects effects = stack.get(TeaComponent.BREW_EFFECTS);
        if (effects != null) {
            effects.first().ifPresent(e -> lines.add(
                    Component.translatable(e.value().getDescriptionId())
                            .append(")")
                            .withStyle(ChatFormatting.BLUE)));
            effects.second().ifPresent(e -> lines.add(
                    Component.translatable(e.value().getDescriptionId()).withStyle(ChatFormatting.BLUE)));
        }
        return new ItemLore(lines);
    }
}
