package ivan.mushroomsdelight.screen;

import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.blocks.TeapotEntity;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TeapotMenu extends AbstractContainerMenu {
    private final Container container;

    private final ContainerData teapotData;
    // Client-side constructor
    public TeapotMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(TeapotEntity.CONTAINER_SIZE), new SimpleContainerData(4));
    }

    // Server-side constructor
    public TeapotMenu(final int containerId, final Inventory inventory, final Container container, ContainerData teapotData) {
        super(ModMenuType.TEAPOT, containerId);
        checkContainerSize(container, TeapotEntity.CONTAINER_SIZE);
        this.container = container;
        this.teapotData = teapotData;
        this.addDataSlots(teapotData);
        // Some containers do custom logic when opened by a player.
        // TODO: is this intended to use this. ?
        container.startOpen(inventory.player);

        this.addSlot(new Slot(container, 0, 25, 18));
        this.addSlot(new Slot(container, 1, 25, 18 + 18));

        this.addSlot(new Slot(container, 2, 31 + 15, 18));
        this.addSlot(new Slot(container, 3, 31 + 15, 18 + 18));

        //mushroom slot
        this.addSlot(new MushroomSlot(container, 4, 81, 26));
        //result
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 5, 135, 26));

        // Add the player inventory slots.
        this.addStandardInventorySlots(inventory, 8, 84);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();

        if (slotIndex < this.container.getContainerSize()) {
            // Слот принадлежит инвентарю чайника (размер 6)
            if (!this.moveItemStackTo(stack, this.container.getContainerSize(), this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // Слот принадлежит инвентарю игрока (хотбар + основной инвентарь)
            if (!this.moveItemStackTo(stack, 0, this.container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return clicked;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public int getCookProgressionScaled() {
        int i = this.teapotData.get(0);
        return i != 0 ? i * 24 / 200 : 0;
    }
}
