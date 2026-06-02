package ivan.mushroomsdelight.tea;

import ivan.mushroomsdelight.items.ModItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
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
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;

public class TeaItem extends ModItem {

    private static final int BASE_DURATION_TICKS = 20 * 20; // 20 секунд в тиках

    private final int finalDurationTicks;
    private final int powerAmplifier;

    public TeaItem(String name, int bonusDurationSeconds, int powerAmplifier) {
        super(buildProperties(), name);

        this.powerAmplifier    = powerAmplifier;
        this.finalDurationTicks = BASE_DURATION_TICKS + bonusDurationSeconds * 20;
    }

    private static Properties buildProperties() {
        return new Properties()
                .stacksTo(16)
                .food(
                        new FoodProperties.Builder()
                                .alwaysEdible()
                                .nutrition(8)
                                .saturationModifier(0.6f)
                                .build(),
                        Consumable.builder()
                                .consumeSeconds(1)
                                .sound(SoundEvents.GENERIC_DRINK)
                                .animation(ItemUseAnimation.DRINK)
                                .hasConsumeParticles(false)
                                .build()
                );
    }

    @Override
    public @NonNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            applyBrewEffects(stack, entity);
        }
        return super.finishUsingItem(stack, level, entity);
    }

    private void applyBrewEffects(ItemStack stack, LivingEntity entity) {
        BrewEffects effects = stack.get(TeaComponent.BREW_EFFECTS);
        if (effects == null) return;

        effects.first().ifPresent(e ->
                entity.addEffect(new MobEffectInstance(e, finalDurationTicks, powerAmplifier)));

        effects.second().ifPresent(e ->
                entity.addEffect(new MobEffectInstance(e, finalDurationTicks, powerAmplifier)));
    }

    public static ItemLore buildLore(ItemStack stack, int durationTicks, int amplifier) {
        BrewEffects effects = stack.get(TeaComponent.BREW_EFFECTS);
        if (effects == null) return new ItemLore(List.of());

        List<Component> lines = new ArrayList<>();

        effects.first().ifPresent(e ->
                lines.add(buildEffectLine(e.value().getDescriptionId(), amplifier, durationTicks)));

        effects.second().ifPresent(e ->
                lines.add(buildEffectLine(e.value().getDescriptionId(), amplifier, durationTicks)));

        return new ItemLore(lines);
    }

    private static Component buildEffectLine(String descriptionId, int amplifier, int durationTicks) {
        return Component.translatable(descriptionId)
                .append(" " + formatAmplifier(amplifier) + " ")
                .append("(" + formatDuration(durationTicks) + ")")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.BLUE)
                        .withItalic(false));
    }

    private static String formatAmplifier(int powerAmplifier) {
        return switch (powerAmplifier + 1) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> "X" + formatAmplifier((powerAmplifier + 1) - 10);
        };
    }

    private static String formatDuration(int ticks) {
        int totalSeconds = ticks / 20;
        int hours   = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%d:%02d", minutes, seconds);
    }

    public int getDurationTicks() {
        return finalDurationTicks;
    }

    public int getPowerAmplifier() {
        return powerAmplifier;
    }
}