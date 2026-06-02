package ivan.mushroomsdelight.networking;

import ivan.mushroomsdelight.HelpFunc;
import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;

public class DoubleJumpPayload implements CustomPacketPayload {
    public static final DoubleJumpPayload INSTANCE = new DoubleJumpPayload();

    public static final Type<DoubleJumpPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "double_jump"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DoubleJumpPayload> CODEC =
            StreamCodec.unit(INSTANCE);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void DoubleJumpRegister(){
        PayloadTypeRegistry.serverboundPlay().register(
                DoubleJumpPayload.TYPE,
                DoubleJumpPayload.CODEC
        );
        ServerPlayNetworking.registerGlobalReceiver(
                DoubleJumpPayload.TYPE,
                (payload, context) -> {

                    ServerLevel serverLevel = context.player().level();
                    Player player = context.player();
                    context.server().execute(() -> {

                        ServerLevel level = serverLevel.getServer().getLevel(serverLevel.dimension());

                        RandomSource random = RandomSource.create();
                        for (int i = 0; i < 15; i++) {
                            level.sendParticles(
                                    ParticlesRegister.MUSHROOM_JUMP,
                                    true, true,
                                    player.getX() + (random.nextFloat() - 0.5f),
                                    player.getY() + (random.nextFloat() - 0.5f),
                                    player.getZ() + (random.nextFloat() - 0.5f),
                                    0,
                                    0,
                                    -5.0,
                                    0,
                                    0.02F
                            );
                        }
                        for (int i = 0; i < 8; i++) {
                            level.sendParticles(
                                    ParticlesRegister.MUSHROOM_JUMP,
                                    true, true,
                                    player.getX() + (random.nextFloat() - 0.5f),
                                    player.getY() + (random.nextFloat() - 0.5f),
                                    player.getZ() + (random.nextFloat() - 0.5f),
                                    0,
                                    random.nextFloat(),
                                    random.nextFloat() - 5.0,
                                    random.nextFloat(),
                                    0.02F
                            );
                        }
                        for (int i = 0; i < 10; i++) {
                            level.sendParticles(
                                    ParticlesRegister.MUSHROOM_JUMP,
                                    true, true,
                                    player.getX() + (random.nextFloat() - 0.5f),
                                    player.getY() + (random.nextFloat() - 0.5f),
                                    player.getZ() + (random.nextFloat() - 0.5f),
                                    0,
                                    HelpFunc.getRandomFloatFromTo(-1.0F, 1.0F, random),
                                    -random.nextFloat(),
                                    HelpFunc.getRandomFloatFromTo(-1.0F, 1.0F, random),
                                    0.08F
                            );
                        }
                        level.playSound(
                                null,
                                player.blockPosition(),
                                SoundEvents.BONE_MEAL_USE,
                                SoundSource.PLAYERS,
                                1f,
                                30
                        );
                    });
                }
        );
    }
}
