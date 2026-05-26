package ivan.mushroomsdelight.data.recipes;

import ivan.mushroomsdelight.items.ModItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

public class CuttingRecipes {
    public static void register(HolderLookup.Provider registryLookup, RecipeOutput output){
        HolderGetter<Item> holderGetter = registryLookup.lookupOrThrow(Registries.ITEM);
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.RED_MUSHROOM),
                Ingredient.of(holderGetter.getOrThrow(vectorwing.farmersdelight.common.tag.CommonTags.Items.TOOLS_KNIFE)),
                ModItems.RED_PIECES, 2)
                .addResultWithChance(Items.BONE_MEAL, 0.5F)
                .save(output);
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.BROWN_MUSHROOM),
                Ingredient.of(holderGetter.getOrThrow(vectorwing.farmersdelight.common.tag.CommonTags.Items.TOOLS_KNIFE)),
                ModItems.BROWN_PIECES, 2)
                .addResultWithChance(Items.BONE_MEAL, 0.5F)
                .save(output);
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.CRIMSON_FUNGUS),
                Ingredient.of(holderGetter.getOrThrow(vectorwing.farmersdelight.common.tag.CommonTags.Items.TOOLS_KNIFE)),
                ModItems.CRIMSON_PIECES, 2)
                .addResultWithChance(Items.BONE_MEAL, 0.5F)
                .addResultWithChance(Items.GOLD_NUGGET, 0.3F)
                .save(output);
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.WARPED_FUNGUS),
                Ingredient.of(holderGetter.getOrThrow(vectorwing.farmersdelight.common.tag.CommonTags.Items.TOOLS_KNIFE)),
                ModItems.WARPED_PIECES, 2)
                .addResultWithChance(Items.BONE_MEAL, 0.5F)
                .addResultWithChance(Items.GOLD_NUGGET, 0.3F)
                .save(output);

    }
}
