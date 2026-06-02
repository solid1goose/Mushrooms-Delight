package ivan.mushroomsdelight.client;
import ivan.mushroomsdelight.client.particles.MushroomJumpParticle;
import ivan.mushroomsdelight.client.particles.SmokeParticle;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import ivan.mushroomsdelight.menu.ModMenuType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.gui.screens.MenuScreens;

public class MushroomsDelightClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MenuScreens.register(ModMenuType.TEAPOT, TeapotScreen::new);
		ParticleProviderRegistry.getInstance().register(ParticlesRegister.SMOKE, SmokeParticle.Provider::new);
		ParticleProviderRegistry.getInstance().register(ParticlesRegister.MUSHROOM_JUMP, MushroomJumpParticle.Provider::new);
	}
}