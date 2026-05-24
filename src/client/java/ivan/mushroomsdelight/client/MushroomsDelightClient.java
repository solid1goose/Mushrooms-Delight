package ivan.mushroomsdelight.client;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

public class MushroomsDelightClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleProviderRegistry.getInstance().register(ParticlesRegister.SMOKE, SmokeParticle.Provider::new);
	}
}