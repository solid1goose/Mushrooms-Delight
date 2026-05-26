package ivan.mushroomsdelight.client;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import ivan.mushroomsdelight.screen.ModMenuType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.gui.screens.MenuScreens;

public class MushroomsDelightClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MenuScreens.register(ModMenuType.TEAPOT, TeapotScreen::new);
		ParticleProviderRegistry.getInstance().register(ParticlesRegister.SMOKE, SmokeParticle.Provider::new);
	}
}