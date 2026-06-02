package ivan.mushroomsdelight.data.recipes;

import ivan.mushroomsdelight.blocks.ModBlocks;
import ivan.mushroomsdelight.data.tags.MushroomTags;
import ivan.mushroomsdelight.items.ModItems;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class CraftingTableRecipes {
    public static void register(HolderLookup.Provider registryLookup, RecipeOutput output){
        HolderGetter<Item> holderGetter = registryLookup.lookupOrThrow(Registries.ITEM);
        ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.FOOD, ModItems.MUSHROOM_POTATO_PIE)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(Items.POTATO)
                .requires(MushroomTags.MUSHROOMS_OVERWORLD)
                .unlockedBy("has_red_mushroom",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_MUSHROOM))
                .unlockedBy("has_brown_mushroom",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.BROWN_MUSHROOM))
                .unlockedBy("has_potato",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .unlockedBy("has_wheat",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT))
                .save(output);

        ShapedRecipeBuilder.shaped(
                holderGetter, RecipeCategory.MISC, ModBlocks.TEAPOT)
                .pattern("III")
                .pattern("I I")
                .pattern("CCC")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_INGOT)
                .unlockedBy("has_iron",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("has_copper",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(output);
    }
}
