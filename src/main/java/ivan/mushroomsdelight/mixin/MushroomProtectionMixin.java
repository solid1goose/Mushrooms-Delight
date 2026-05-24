package ivan.mushroomsdelight.mixin;

import ivan.mushroomsdelight.effects.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class MushroomProtectionMixin {
    @ModifyVariable(
            method = "hurtServer",
            at = @At("HEAD"),
            argsOnly = true,
            name = "damage"
    )

    private float protect(float damage, ServerLevel level, DamageSource source) {
        LivingEntity self = (LivingEntity)(Object) this;
        MobEffectInstance effect = self.getEffect(ModEffects.MUSHROOM_PROTECTION);
        if (effect != null) {
            int previousAmp = effect.getAmplifier();
            int previousDuration = effect.getDuration();

            self.removeEffect(ModEffects.MUSHROOM_PROTECTION);

            if (previousAmp != 0) {
                self.addEffect(new MobEffectInstance(
                        ModEffects.MUSHROOM_PROTECTION,
                        previousDuration,
                        previousAmp - 1
                ));
            }
            return 0f;
        }
        return damage;
    }
}
