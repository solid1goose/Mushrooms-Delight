package ivan.mushroomsdelight.data.recipes;

import ivan.mushroomsdelight.MushroomsDelight;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class MushroomRecipes extends FabricRecipeProvider {
    public MushroomRecipes(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                CuttingRecipes.register(registries, this.output);
                FurnaceRecipes.register(registries,this.output);
                CraftingTableRecipes.register(registries, this.output);
                PotRecipes.register(registries, this.output);
            }
        };
    }

    @Override
    public String getName() {
        return MushroomsDelight.modName + " recipes";
    }
}
