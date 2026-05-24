package ivan.mushroomsdelight;

import ivan.mushroomsdelight.effects.ModEffects;
import ivan.mushroomsdelight.events.AttackEntity;
import ivan.mushroomsdelight.events.EntityTakeDamage;
import ivan.mushroomsdelight.items.ModItems;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MushroomsDelight implements ModInitializer {
	public static final String modName = "Mushrooms Delight";
	public static final String MOD_ID = "mushrooms-delight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CreativeTabWindow.CreativeTabWindowRegister();
		ModItems.initialize();
		ModEffects.initialize();
		ParticlesRegister.register();
		AttackEntity.register();
//		EntityTakeDamage.register();
	}
}