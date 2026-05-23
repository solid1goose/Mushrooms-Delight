package ivan.mushroomsdelight.items;

import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.items.moditems.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static List<ModItem> newItems = new ArrayList<>();

    public static final ModItem CHICKEN_STUFFED_MUSHROOMS = new ChickenStuffedMushrooms();
    public static final ModItem SPIDER_EYES_SOUP = new SpiderEyesSoupItem();
    public static final ModItem MUSHROOM_BROTH = new MushroomBroth();
    public static final ModItem MUSHROOM_POTATO_PIE = new MushroomPotatoPie();
    public static final ModItem RISOTTO = new Risotto();
    public static final ModItem JULIENNE = new Julienne();

    public static final ModItem TEA_SPEED = new TeaSpeed();
    public static final ModItem TEA_MINE = new TeaMine();

    public static void initialize() {
        newItems.forEach(ModItems::register);
    }

    public static <T extends Item> void register(ModItem item) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, item.getItemName()));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }
}
