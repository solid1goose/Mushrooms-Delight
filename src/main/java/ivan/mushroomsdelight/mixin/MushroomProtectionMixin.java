package ivan.mushroomsdelight.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import ivan.mushroomsdelight.effects.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public class MushroomProtectionMixin {

    @ModifyArg(
            method = "hurtServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V"
            ),
            index = 2
    )
    private float modifyDamage(
            float damage,
            @Local(argsOnly = true, name = "level") ServerLevel level
    ) {

        LivingEntity self = (LivingEntity)(Object)this;

        MobEffectInstance effect =
                self.getEffect(ModEffects.MUSHROOM_PROTECTION);

        if (effect != null) {

            int amp = effect.getAmplifier();
            int duration = effect.getDuration();

            self.removeEffect(ModEffects.MUSHROOM_PROTECTION);

            if (amp > 0) {
                self.addEffect(new MobEffectInstance(
                        ModEffects.MUSHROOM_PROTECTION,
                        duration,
                        amp - 1
                ));
            }

            level.playSound(
                    null,
                    self.blockPosition(),
                    SoundEvents.HANGING_ROOTS_BREAK,
                    SoundSource.PLAYERS,
                    1f,
                    1f
            );

            return 0f;
        }

        return damage;
    }
}
