package ivan.mushroomsdelight.recipes;

import ivan.mushroomsdelight.items.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class FurnaceRecipes {
    public static void register(HolderLookup.Provider registryLookup, RecipeOutput output){
        addCookingRecipes(output, Items.RED_MUSHROOM, ModItems.FRIED_RED, 0.35F, false);
        addCookingRecipes(output, Items.BROWN_MUSHROOM, ModItems.FRIED_BROWN, 0.35F, false);
        addCookingRecipes(output, Items.CRIMSON_FUNGUS, ModItems.FRIED_CRIMSON, 0.35F, false);
        addCookingRecipes(output, Items.WARPED_FUNGUS, ModItems.FRIED_WARPED, 0.35F, false);

        addCookingRecipes(output, ModItems.RED_PIECES, ModItems.DRIED_RED_PIECES, 0.35F, true);
        addCookingRecipes(output, ModItems.BROWN_PIECES, ModItems.DRIED_BROWN_PIECES, 0.35F, true);
        addCookingRecipes(output, ModItems.CRIMSON_PIECES, ModItems.DRIED_CRIMSON_PIECES, 0.35F, true);
        addCookingRecipes(output, ModItems.WARPED_PIECES, ModItems.DRIED_WARPED_PIECES, 0.35F, true);
    }

    private static void addCookingRecipes(RecipeOutput output, ItemLike input, ItemLike result, float xp, boolean onlySmokeBuild) {
        Ingredient ingredient = Ingredient.of(input);
        String resultPath = BuiltInRegistries.ITEM.getKey(result.asItem()).getPath();
        String unlockName = "has_" + BuiltInRegistries.ITEM.getKey(input.asItem()).getPath();
        var trigger = InventoryChangeTrigger.TriggerInstance.hasItems(input);
        if (!onlySmokeBuild){
            SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.FOOD, CookingBookCategory.FOOD,result, xp, 200)
                    .unlockedBy(unlockName, trigger)
                    .save(output, String.valueOf(Identifier.fromNamespaceAndPath("mushrooms-delight",
                            resultPath + "_from_smelting")));
        }

        SimpleCookingRecipeBuilder.smoking(ingredient, RecipeCategory.FOOD, result, xp, 100)
                .unlockedBy(unlockName, trigger)
                .save(output, String.valueOf(Identifier.fromNamespaceAndPath("mushrooms-delight",
                        resultPath + "_from_smoking")));
    }
}
