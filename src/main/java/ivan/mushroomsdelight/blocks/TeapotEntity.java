package ivan.mushroomsdelight.blocks;

import ivan.mushroomsdelight.data.tags.MushroomTags;
import ivan.mushroomsdelight.items.ModItems;
import ivan.mushroomsdelight.menu.TeapotMenu;
import ivan.mushroomsdelight.tea.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;

import java.util.Objects;
import java.util.Optional;

public class TeapotEntity extends BlockEntity implements ImplementedContainer, MenuProvider, HeatableBlockEntity {
    public static final int CONTAINER_SIZE = 6;

    private static final int MUSHROOM_SLOT = 4;
    private static final int RESULT_SLOT = 5;

    private static final int MAX_BREW_TIME = 200;

    public boolean isHeated = false;

    protected final ContainerData teapotData;
    public int brewTime = 0;

    private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public TeapotEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntityType.TEAPOT_ENTITY, worldPosition, blockState);
        this.teapotData = this.createIntArray();
    }

    public static void brewingTick(ServerLevel level, BlockPos pos, BlockState state, TeapotEntity teapotEntity){
        if (!teapotEntity.isHeated(level, pos)){
            teapotEntity.isHeated = false;
            return;
        }
        teapotEntity.isHeated = true;
        brewing(teapotEntity);
    }

    public static void brewing(TeapotEntity teapotEntity) {
        if (!canBrew(teapotEntity)) {
            teapotEntity.brewTime = 0;
            teapotEntity.teapotData.set(0, teapotEntity.brewTime);
            return;
        }

        if (++teapotEntity.brewTime >= MAX_BREW_TIME) {
            finishBrew(teapotEntity);
            teapotEntity.brewTime = 0;
        }

        teapotEntity.teapotData.set(0, teapotEntity.brewTime);
    }

    private static boolean sameTeaEffects(ItemStack stack1, ItemStack stack2) {
        BrewEffects effects1 = stack1.get(TeaComponent.BREW_EFFECTS);
        BrewEffects effects2 = stack2.get(TeaComponent.BREW_EFFECTS);

        return Objects.equals(effects1, effects2);
    }

    private static ItemStack getBrewingTea(TeapotEntity teapotEntity){
        Holder<MobEffect> teaEffect1 = getEffectFromRecipe(0, 1, teapotEntity);
        Holder<MobEffect> teaEffect2 = getEffectFromRecipe(2, 3, teapotEntity);
        Item mushroom_pieces = teapotEntity.getItem(MUSHROOM_SLOT).getItem();

        ItemStack tea = getTeaType(mushroom_pieces);

        tea.set(TeaComponent.BREW_EFFECTS, new BrewEffects(
                Optional.ofNullable(teaEffect1),
                Optional.ofNullable(teaEffect2)
        ));
        TeaItem teaItem = (TeaItem) tea.getItem();
        tea.set(DataComponents.LORE, TeaItem.buildLore(tea, teaItem.getDurationTicks(), teaItem.getPowerAmplifier()));
        return tea;
    }

    private static ItemStack getTeaType(Item mushroom_pieces) {
        ItemStack tea = new ItemStack(ModItems.TEA_TIME);

        if(mushroom_pieces == ModItems.DRIED_BROWN_PIECES){
            tea = new ItemStack(ModItems.TEA_TIME);
        } else if (mushroom_pieces == ModItems.DRIED_RED_PIECES){
            tea = new ItemStack(ModItems.TEA_AMPLIFIED);
        } else if (mushroom_pieces == ModItems.DRIED_CRIMSON_PIECES){
            tea = new ItemStack(ModItems.TEA_AMPLIFIED_HELL);
        } else if (mushroom_pieces == ModItems.DRIED_WARPED_PIECES){
            tea = new ItemStack(ModItems.TEA_TIME_HELL);
        }
        return tea;
    }

    private static boolean canBrew(TeapotEntity teapotEntity){
        ItemStack brewingTea = getBrewingTea(teapotEntity);
        ItemStack resultTea = teapotEntity.getItem(RESULT_SLOT);

        return twoSlotsCheck(0, 1, teapotEntity) &&
                twoSlotsCheck(2, 3, teapotEntity) &&
                mushroomPiecesIn(teapotEntity) &&
                (
                        resultTea.isEmpty() ||
                                (
                                        ItemStack.isSameItem(resultTea, brewingTea) &&
                                                sameTeaEffects(resultTea, brewingTea)
                                )
                );
    }

    private static boolean mushroomPiecesIn(TeapotEntity teapotEntity){
        return teapotEntity.getItem(MUSHROOM_SLOT).is(MushroomTags.MUSHROOM_DRIED_PIECES);
    }

    private static boolean twoSlotsCheck(int slot1, int slot2, TeapotEntity teapotEntity){
        if (
                TeaRecipes.isOneEquals(new Item[]{teapotEntity.getItem(slot1).getItem(), teapotEntity.getItem(slot2).getItem()}) ||
                teapotEntity.getItem(slot1) == ItemStack.EMPTY && teapotEntity.getItem(slot2) == ItemStack.EMPTY
        ){
            return true;
        }
        return false;
    }

    @Nullable
    private static Holder<MobEffect> getEffectFromRecipe(int slot1, int slot2, TeapotEntity teapotEntity){
        TeaRecipe recipe = TeaRecipes.getRecipe(new Item[]{teapotEntity.getItem(slot1).getItem(), teapotEntity.getItem(slot2).getItem()});
        return recipe == null? null : recipe.TEA_EFFECT;
    }

    private static void finishBrew(TeapotEntity teapotEntity){
        ItemStack resultItemStack = teapotEntity.getItem(RESULT_SLOT);
        if (resultItemStack != ItemStack.EMPTY) {
            resultItemStack.setCount(resultItemStack.getCount() + 1);
        } else {
            teapotEntity.setItem(RESULT_SLOT, getBrewingTea(teapotEntity));
        }
        teapotEntity.removeItem(0, 1);
        teapotEntity.removeItem(1, 1);
        teapotEntity.removeItem(2, 1);
        teapotEntity.removeItem(3, 1);
        teapotEntity.removeItem(MUSHROOM_SLOT, 1);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.items);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.items);
        super.saveAdditional(output);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("Teapot");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TeapotMenu(containerId, inventory, this, teapotData);
    }

    private ContainerData createIntArray() {
        return new ContainerData() {
            {
                Objects.requireNonNull(TeapotEntity.this);
            }

            public int get(int index) {
                switch (index) {
                    case 0: return TeapotEntity.this.brewTime;
                    case 1: return TeapotEntity.this.isHeated ? 1 : 0;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TeapotEntity.this.brewTime = value;
                    case 1 -> TeapotEntity.this.isHeated = value != 0;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        if (slot == 5){
            return false;
        }
        if (slot == 4){
            return itemStack.is(MushroomTags.MUSHROOM_DRIED_PIECES);
        }
        return true;
    }

    @Override
    public boolean canTakeItem(Container into, int slot, ItemStack itemStack) {
        return slot == 6;
    }
}
