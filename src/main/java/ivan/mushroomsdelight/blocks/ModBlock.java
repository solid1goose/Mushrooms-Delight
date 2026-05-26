package ivan.mushroomsdelight.blocks;

import ivan.mushroomsdelight.MushroomsDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public abstract class ModBlock extends Block{

    public boolean shouldRegisterItem = true;
    public String name = null;
    public ResourceKey<Block> blockKey = null;

    public ModBlock(String name, BlockBehaviour.Properties settings, boolean shouldRegisterItem){
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, name));
        super(settings.setId(key));
        this.shouldRegisterItem = shouldRegisterItem;
        this.name = name;
        this.blockKey = key;
    }
}
