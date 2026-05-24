package ivan.mushroomsdelight.events;

import ivan.mushroomsdelight.effects.ModEffects;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class AttackEntity {
    public static void register(){
        AttackEntityCallback.EVENT.register((player, world, entity, target, hitResult) -> {
            MobEffectInstance effect = player.getEffect(ModEffects.MUSHROOM_TOUCH);
            if (effect != null) {
                LivingEntity livingEntity = (LivingEntity) target;
                livingEntity.addEffect( new MobEffectInstance(
                                ModEffects.FUNGAL_INFECTION,
                                50,
                                effect.getAmplifier()
                        )
                );
            }
            return InteractionResult.PASS;
        });
    }
}
