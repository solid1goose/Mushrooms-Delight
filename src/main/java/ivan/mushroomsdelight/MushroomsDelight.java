package ivan.mushroomsdelight;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import ivan.mushroomsdelight.blocks.ModBlockEntityType;
import ivan.mushroomsdelight.blocks.ModBlocks;
import ivan.mushroomsdelight.effects.ModEffects;
import ivan.mushroomsdelight.events.AttackEntity;
import ivan.mushroomsdelight.items.ModItems;
import ivan.mushroomsdelight.networking.ServerNetworking;
import ivan.mushroomsdelight.particles.ParticlesRegister;
import ivan.mushroomsdelight.tea.TeaComponent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MushroomsDelight implements ModInitializer {
	public static final String modName = "Mushrooms Delight";
	public static final String MOD_ID = "mushrooms-delight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static int test1 = 0;
	public static int test2 = 0;

	@Override
	public void onInitialize() {
		ServerNetworking.register();
		CreativeTabWindow.CreativeTabWindowRegister();
		ModItems.initialize();
		ModEffects.initialize();
		ParticlesRegister.register();
		AttackEntity.register();
		ModBlocks.initialize();
		ModBlockEntityType.initialize();
		TeaComponent.register();


		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {
			dispatcher.register(Commands.literal("setTest1")
					.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
					.then(Commands.argument("test1", IntegerArgumentType.integer())
							.executes(context -> {
								this.test1 = IntegerArgumentType.getInteger(context, "test1");
								return 1;
							})
					)
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {
			dispatcher.register(Commands.literal("setTest2")
					.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
					.then(Commands.argument("test2", IntegerArgumentType.integer())
							.executes(context -> {
								this.test2 = IntegerArgumentType.getInteger(context, "test2");
								return 1;
							})
					)
			);
		});
	}
}