package ivan.mushroomsdelight.particles;

import ivan.mushroomsdelight.MushroomsDelight;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ParticlesRegister {
    public static final SimpleParticleType SMOKE = FabricParticleTypes.simple();
    public static void register(){
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "smoke"), SMOKE);
    }
}
