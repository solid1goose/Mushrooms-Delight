package ivan.mushroomsdelight.data.tags;

import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class MushroomTags extends FabricTagsProvider.ItemTagsProvider {

    public static final TagKey<Item> MUSHROOM_DRIED_PIECES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_dried_pieces"));

    public MushroomTags(FabricPackOutput output, CompletableFuture registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        valueLookupBuilder(MUSHROOM_DRIED_PIECES)
                .add(ModItems.DRIED_BROWN_PIECES)
                .add(ModItems.DRIED_RED_PIECES)
                .add(ModItems.DRIED_CRIMSON_PIECES)
                .add(ModItems.DRIED_WARPED_PIECES);
    }
}
