package ivan.mushroomsdelight.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public abstract class SmokableItem extends ModItem{
    public SmokableItem(Properties properties, String itemName) {
        super(properties, itemName);
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return 30;
    }

    @Override
    public @NonNull ItemUseAnimation getUseAnimation(final ItemStack itemStack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        player.getCooldowns().addCooldown(this.getDefaultInstance(), 50);
        return InteractionResult.SUCCESS;
    }

    @Override
    public abstract @NonNull ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity);
}
