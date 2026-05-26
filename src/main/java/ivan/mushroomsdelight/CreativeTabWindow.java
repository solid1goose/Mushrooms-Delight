package ivan.mushroomsdelight;

import ivan.mushroomsdelight.blocks.ModBlocks;
import ivan.mushroomsdelight.items.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CreativeTabWindow {
    public static void CreativeTabWindowRegister(){
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }
    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "creative_tab")
    );
    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(Items.RED_MUSHROOM))
            .title(Component.translatable(MushroomsDelight.modName))
            .displayItems((params, output) -> {
                for (int i = 0; i < ModItems.newItems.size();i++){
                    output.accept(ModItems.newItems.get(i).asItem());
                }
                output.accept(ModBlocks.TEAPOT);
            })
            .build();
}
