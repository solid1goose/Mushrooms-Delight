package ivan.mushroomsdelight.recipes;

import ivan.mushroomsdelight.items.ModItems;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
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
                .requires(ConventionalItemTags.MUSHROOMS)
                .unlockedBy("open_mushroom_pie",
                        InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{Items.RED_MUSHROOM, Items.BROWN_MUSHROOM}))
                .save(output);
    }
}
