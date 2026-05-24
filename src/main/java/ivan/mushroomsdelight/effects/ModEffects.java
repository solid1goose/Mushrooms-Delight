package ivan.mushroomsdelight.effects;

import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.effects.musroomeffects.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;
import  net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

public class ModEffects {

    public static final Holder<MobEffect> MUSHROOM_JUMP;
    public static final Holder<MobEffect> MUSHROOM_PROTECTION;
    public static final Holder<MobEffect> FUNGAL_INFECTION;
    public static final Holder<MobEffect> MUSHROOM_TOUCH;
    public static final Holder<MobEffect> MUSHROOM_SPEED;

    public static void initialize(){}

    static {
        MUSHROOM_JUMP = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,  Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_jump"), new MushroomJump());
        MUSHROOM_PROTECTION = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,  Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_protection"), new MushroomProtection());
        FUNGAL_INFECTION = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,  Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "fungal_infection"), new FungalInfection());
        MUSHROOM_TOUCH = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,  Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_touch"), new MushroomTouch());
        MUSHROOM_SPEED = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,  Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_speed"), new MushroomSpeed()
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "mushroom_speed_effect"), (double) 0.07F, Operation.ADD_MULTIPLIED_TOTAL));
    }
}
