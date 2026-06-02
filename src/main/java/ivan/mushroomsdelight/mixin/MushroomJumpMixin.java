package ivan.mushroomsdelight.mixin;

import ivan.mushroomsdelight.networking.DoubleJumpPayload;
import ivan.mushroomsdelight.effects.ModEffects;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MushroomJumpMixin {

    @Shadow
    public abstract boolean isJumping();

    @Shadow
    public abstract void jumpFromGround();

    private int currentJumps = 0;
    private boolean wasJumpingLastTick = false;
    private boolean releasedJumpInAir = false;

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void onAiStep(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object) this;
        if (!self.hasEffect(ModEffects.MUSHROOM_JUMP) || !(self instanceof Player player)){
            return;
        }
        boolean currentlyJumping = isJumping();
        boolean onGround = self.onGround();

        // Сброс при приземлении
        if (onGround) {
            releasedJumpInAir = false;
            currentJumps = self.getEffect(ModEffects.MUSHROOM_JUMP).getAmplifier() + 1;
        }

        // Фиксируем что игрок отпустил прыжок находясь в воздухе
        if (!onGround && wasJumpingLastTick && !currentlyJumping) {
            releasedJumpInAir = true;
        }

        // Двойной прыжок: в воздухе, нажал прыжок, до этого отпускал, ещё не прыгал дважды
        if (!onGround && currentlyJumping && releasedJumpInAir && currentJumps != 0) {
            jumpFromGround();
            releasedJumpInAir = false; // чтобы не прыгнуть снова пока не отпустит
            currentJumps--;
            ClientPlayNetworking.send(
                    DoubleJumpPayload.INSTANCE
            );
        }

        wasJumpingLastTick = currentlyJumping;
    }
}
