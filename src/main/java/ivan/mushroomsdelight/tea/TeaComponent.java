package ivan.mushroomsdelight.tea;

import ivan.mushroomsdelight.MushroomsDelight;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class TeaComponent{
    public static final DataComponentType<BrewEffects> BREW_EFFECTS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID, "brew_effects"),
            DataComponentType.<BrewEffects>builder().persistent(BrewEffects.CODEC).build()
    );

    public static void register() {} // вызови в инициализации мода чтобы класс загрузился
}