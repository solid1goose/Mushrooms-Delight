package ivan.mushroomsdelight.tea;

import ivan.mushroomsdelight.effects.ModEffects;
import ivan.mushroomsdelight.items.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;

public class TeaRecipes {
    public static ArrayList<TeaRecipe> allRecipes = new ArrayList<TeaRecipe>();
    public static final TeaRecipe SPEED = new TeaRecipe(
            Items.SUGAR,
            ModItems.BROWN_PIECES,
            ModEffects.MUSHROOM_SPEED
    );
    public static final TeaRecipe JUMP = new TeaRecipe(
            Items.REDSTONE,
            ModItems.BROWN_PIECES,
            ModEffects.MUSHROOM_JUMP
    );

    public static boolean isOneEquals(Item[] items){
        for (int i = 0; i < allRecipes.size(); i++) {
            if (allRecipes.get(i).isValidRecipe(items)) {
                return true;
            }
        }
        return false;
    };

    public static TeaRecipe getRecipe(Item[] items){
        for (int i = 0; i < allRecipes.size(); i++) {
            if (allRecipes.get(i).isValidRecipe(items)) {
                return allRecipes.get(i);
            }
        }
        return null;
    };
}
