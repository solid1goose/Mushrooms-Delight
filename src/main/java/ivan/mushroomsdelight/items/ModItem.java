package ivan.mushroomsdelight.items;

import ivan.mushroomsdelight.MushroomsDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

// Базовый абстрактный класс
public abstract class ModItem extends Item {
    private String itemName = null;
    public ModItem(Properties properties, String itemName) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, itemName));
        properties.setId(itemKey);
        super(properties);
        this.itemName = itemName;
        ModItems.newItems.add(this);
    }

    protected static Properties defaultProperties() {
        return new Properties();
    }

    protected String getItemName(){
        return this.itemName;
    };
}
