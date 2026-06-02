package ivan.mushroomsdelight.menu;

import ivan.mushroomsdelight.data.tags.MushroomTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MushroomSlot extends Slot {
    public MushroomSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return itemStack.is(MushroomTags.MUSHROOM_DRIED_PIECES);
    }
}
