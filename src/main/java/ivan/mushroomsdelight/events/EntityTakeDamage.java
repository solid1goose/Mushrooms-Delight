package ivan.mushroomsdelight.events;

import ivan.mushroomsdelight.effects.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class EntityTakeDamage {
    public static void register(){
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            MobEffectInstance effect = entity.getEffect(ModEffects.MUSHROOM_PROTECTION);
            if (effect != null) {
                int previousAmp = effect.getAmplifier();
                int previousDuration = effect.getDuration();

                entity.removeEffect(ModEffects.MUSHROOM_PROTECTION);

                if (previousAmp != 0) {
                    entity.addEffect(new MobEffectInstance(
                            ModEffects.MUSHROOM_PROTECTION,
                            previousDuration,
                            previousAmp - 1
                    ));
                }

                return false;
            }
            return true;
        });
    }
}
