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

    public static final ModItem FRIED_RED = new FriedRed();
    public static final ModItem FRIED_BROWN = new FriedBrown();
    public static final ModItem FRIED_CRIMSON = new FriedCrimsonFungus();
    public static final ModItem FRIED_WARPED = new FriedWarpedFungus();

    public static final ModItem CHICKEN_STUFFED_MUSHROOMS = new ChickenStuffedMushrooms();
    public static final ModItem SPIDER_EYES_SOUP = new SpiderEyesSoupItem();
    public static final ModItem MUSHROOM_BROTH = new MushroomBroth();
    public static final ModItem MUSHROOM_POTATO_PIE = new MushroomPotatoPie();
    public static final ModItem RISOTTO = new Risotto();
    public static final ModItem JULIENNE = new Julienne();

    public static final ModItem RED_PIECES = new RedPieces();
    public static final ModItem DRIED_RED_PIECES = new DriedRedPieces();
    public static final ModItem BROWN_PIECES = new BrownPieces();
    public static final ModItem DRIED_BROWN_PIECES = new DriedBrownPieces();

    public static final ModItem CRIMSON_PIECES = new CrimsonPieces();
    public static final ModItem DRIED_CRIMSON_PIECES = new DriedCrimson();
    public static final ModItem WARPED_PIECES = new WarpedPieces();
    public static final ModItem DRIED_WARPED_PIECES = new DriedWarped();

    public static final ModItem TEA_SPEED = new TeaSpeed();
    public static final ModItem TEA_MINE = new TeaMine();

    public static final ModItem BONG = new Bong();
    public static final ModItem CIGARETTE = new Cigarette();

    public static void initialize() {
        newItems.forEach(ModItems::register);
    }

    public static <T extends Item> void register(ModItem item) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, item.getItemName()));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }
}
