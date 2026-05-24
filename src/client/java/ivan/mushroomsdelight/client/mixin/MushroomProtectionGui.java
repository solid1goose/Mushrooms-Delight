package ivan.mushroomsdelight.client.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.effects.ModEffects;
import ivan.mushroomsdelight.effects.musroomeffects.MushroomProtection;
import net.fabricmc.fabric.mixin.client.rendering.GuiAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class MushroomProtectionGui {

    private static final Identifier MY_ICON =
            Identifier.fromNamespaceAndPath(
                    "mushrooms-delight",
                    "textures/hud/mushroom_protection_icon.png"
            );

    @Inject(
            method = "extractPlayerHealth(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V",
            at = @At("TAIL")
    )
    private void renderMushroomProtection(
            GuiGraphicsExtractor graphics,
            CallbackInfo ci
    ) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        MobEffectInstance effect = player.getEffect(ModEffects.MUSHROOM_PROTECTION);
        if (effect != null) {
            for (int i = 0; i <= effect.getAmplifier(); i++) {
                int x = graphics.guiWidth() / 2 - 22 - i * 8;
                int y = graphics.guiHeight() - 43;

                graphics.blit(
                        RenderPipelines.GUI_TEXTURED,
                        MY_ICON,
                        x,
                        y,
                        0,
                        0,
                        16,
                        16,
                        16,
                        16
                );
            }
        }
    }
}
