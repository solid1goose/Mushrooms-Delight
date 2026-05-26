package ivan.mushroomsdelight.tea;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

public class TeaRecipe {
    public Item INGREDIENT1 = null;
    public Item INGREDIENT2 = null;
    public Holder<MobEffect> TEA_EFFECT = null;

    public TeaRecipe(Item ingredient1, Item ingredient2, Holder<MobEffect> effect){
        this.INGREDIENT1 = ingredient1;
        this.INGREDIENT2 = ingredient2;
        this.TEA_EFFECT = effect;
        TeaRecipes.allRecipes.add(this);
    }

    public boolean isValidRecipe(Item[] items) {
        boolean has1 = false;
        boolean has2 = false;
        for (Item item : items) {
            if (item == INGREDIENT1) has1 = true;
            if (item == INGREDIENT2) has2 = true;
        }
        return has1 && has2;
    }
}
