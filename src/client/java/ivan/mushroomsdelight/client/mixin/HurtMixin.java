package ivan.mushroomsdelight.client.mixin;

import ivan.mushroomsdelight.client.IHudShake;
import ivan.mushroomsdelight.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class HurtMixin {
    @Inject(
            method = "handleDamageEvent",
            at = @At("HEAD"),
            cancellable = true
    )
    public void hurt(DamageSource source, CallbackInfo ci){
        LivingEntity self = (LivingEntity)(Object) this;
        if (self == Minecraft.getInstance().player && self.hasEffect(ModEffects.MUSHROOM_PROTECTION)) {
            ((IHudShake) Minecraft.getInstance().gui).triggerShake();
            self.playSound(
                    SoundEvents.HANGING_ROOTS_BREAK,
                    100,
                    -50
            );
        }
    }
}
