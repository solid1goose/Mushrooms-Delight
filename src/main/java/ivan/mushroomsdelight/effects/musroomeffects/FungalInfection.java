package ivan.mushroomsdelight.effects.musroomeffects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FungalInfection extends MobEffect {
    public FungalInfection() {
        super(MobEffectCategory.HARMFUL, 15971072);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return tickCount % 10 == 0;
    }

    @Override
    public boolean applyEffectTick(final ServerLevel serverLevel, final LivingEntity mob, final int amplification) {
        if(mob.isAlive()) {
            DamageSource damageSource = serverLevel.damageSources().magic();
            mob.hurtServer(serverLevel, damageSource, 0.5F + (amplification * 0.5F));
        }
        return true;
    }
}
