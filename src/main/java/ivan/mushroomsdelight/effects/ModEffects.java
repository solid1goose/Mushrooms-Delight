package ivan.mushroomsdelight.effects;

import ivan.mushroomsdelight.MushroomsDelight;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;
import vectorwing.farmersdelight.FarmersDelight;
import  net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

public class ModEffects {
    public static final Holder<MobEffect> MUSHROOM_SPEED;

    public static void initialize(){}

    static {
        MUSHROOM_SPEED = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,  Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_speed"), new MushroomSpeed()
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_speed_effect"), (double) 0.07F, Operation.ADD_MULTIPLIED_TOTAL));
    }
}
