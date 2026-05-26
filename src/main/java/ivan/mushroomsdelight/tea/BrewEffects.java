package ivan.mushroomsdelight.tea;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.Optional;

public record BrewEffects(
        Optional<Holder<MobEffect>> first,
        Optional<Holder<MobEffect>> second
) {
    public static final Codec<BrewEffects> CODEC = RecordCodecBuilder.create(i -> i.group(
            MobEffect.CODEC.optionalFieldOf("first").forGetter(BrewEffects::first),
            MobEffect.CODEC.optionalFieldOf("second").forGetter(BrewEffects::second)
    ).apply(i, BrewEffects::new));
}