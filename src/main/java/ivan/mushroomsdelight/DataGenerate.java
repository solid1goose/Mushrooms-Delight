package ivan.mushroomsdelight;

import ivan.mushroomsdelight.data.recipes.MushroomRecipes;
import ivan.mushroomsdelight.data.tags.MushroomTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerate implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator){
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(MushroomRecipes::new);
        pack.addProvider(MushroomTags::new);
    }
}
