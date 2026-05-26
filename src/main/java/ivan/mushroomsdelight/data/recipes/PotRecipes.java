package ivan.mushroomsdelight.data.recipes;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import ivan.mushroomsdelight.items.ModItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.crafting.CookingPotBookCategory;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import static vectorwing.farmersdelight.common.registry.ModItems.*;


public class PotRecipes {
    public static void register(HolderLookup.Provider registryLookup, RecipeOutput output){
        HolderGetter<Item> holderGetter = registryLookup.lookupOrThrow(Registries.ITEM);
        CookingPotRecipeBuilder.cookingPotRecipe(holderGetter,
                ModItems.SPIDER_EYES_SOUP, 1, 200, 1.0F)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.ROTTEN_FLESH)
                .unlockedByAnyIngredient(new ItemLike[]{Items.SPIDER_EYE, Items.ROTTEN_FLESH, Items.RED_MUSHROOM, Items.BROWN_MUSHROOM})
                .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                .save(output);

        CookingPotRecipeBuilder.cookingPotRecipe(holderGetter,
                ModItems.MUSHROOM_BROTH, 1, 100, 1.0F)
                .addIngredient(ConventionalItemTags.MUSHROOMS)
                .addIngredient(Items.REDSTONE)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(new ItemLike[]{Items.SUGAR, Items.RED_MUSHROOM, Items.BROWN_MUSHROOM})
                .setRecipeBookCategory(CookingPotBookCategory.DRINKS)
                .save(output);

        CookingPotRecipeBuilder.cookingPotRecipe(holderGetter,
                 ModItems.CHICKEN_STUFFED_MUSHROOMS, 1, 200, 1.0F)
                .addIngredient(Items.CHICKEN)
                .addIngredient(ConventionalItemTags.MUSHROOMS)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(CABBAGE_LEAF.get())
                .unlockedByAnyIngredient(new ItemLike[]{Items.CHICKEN, Items.RED_MUSHROOM, Items.BROWN_MUSHROOM})
                .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                .save(output);

        CookingPotRecipeBuilder.cookingPotRecipe(holderGetter,
                        ModItems.RISOTTO, 1, 200, 1.0F)
                .addIngredient(RICE.get())
                .addIngredient(ConventionalItemTags.MUSHROOMS)
                .addIngredient(ONION.get())
                .unlockedByAnyIngredient(new ItemLike[]{RICE.get(), ONION.get(), Items.RED_MUSHROOM, Items.BROWN_MUSHROOM})
                .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                .save(output);

        CookingPotRecipeBuilder.cookingPotRecipe(holderGetter,
                        ModItems.JULIENNE, 1, 200, 1.0F)
                .addIngredient(CommonTags.Items.FOODS_RAW_CHICKEN)
                .addIngredient(ConventionalItemTags.MUSHROOMS)
                .addIngredient(ONION.get())
                .unlockedByAnyIngredient(new ItemLike[]{RICE.get(), ONION.get(), Items.RED_MUSHROOM, Items.BROWN_MUSHROOM})
                .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                .save(output);
    }
}
