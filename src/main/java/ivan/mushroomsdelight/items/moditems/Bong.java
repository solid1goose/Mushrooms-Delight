package ivan.mushroomsdelight.items.moditems;

import ivan.mushroomsdelight.items.SmokableItem;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class Bong extends SmokableItem {
    public Bong(){
        super(new Properties()
                        .stacksTo(1)
                , "bong");
    }

    @Override
    public @NonNull ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        if (entity instanceof Player player) {
            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                RandomSource random = RandomSource.create();
                Vec3 look = player.getLookAngle();
                double spread = 0.7;
                for (int i = 0; i < 12; i++) {
                    double velX = look.x + (random.nextFloat() - 0.5f) * spread;
                    double velY = look.y + (random.nextFloat() - 0.5f) * spread;
                    double velZ = look.z + (random.nextFloat() - 0.5f) * spread;
                    serverLevel.sendParticles(
                            ParticlesRegister.SMOKE,
                            true, true,
                            player.getX(),
                            player.getEyeY(),
                            player.getZ(),
                            0,
                            velX,
                            velY,
                            velZ,
                            0.02 + random.nextFloat() * 0.05F
                    );
                }
                serverLevel.sendParticles(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        player.getX(),
                        player.getY() + 1,
                        player.getZ(),
                        20,
                        0.5, 0.5, 0.5,
                        0.0
                );
                serverLevel.sendParticles(
                        ParticleTypes.LARGE_SMOKE,
                        player.getX(),
                        player.getY() + 1,
                        player.getZ(),
                        10,
                        0.5, 0.5, 0.5,
                        0.0
                );
                player.addEffect(new MobEffectInstance(
                        MobEffects.SPEED,
                        50,
                        2
                ));
            }
        }
        return stack;
    }
}
