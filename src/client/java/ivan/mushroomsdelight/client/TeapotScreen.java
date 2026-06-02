package ivan.mushroomsdelight.client;

import ivan.mushroomsdelight.MushroomsDelight;
import ivan.mushroomsdelight.menu.TeapotMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import java.awt.*;

public class TeapotScreen extends AbstractContainerScreen<TeapotMenu> {
    private static final Identifier CONTAINER_TEXTURE = Identifier.fromNamespaceAndPath(MushroomsDelight.MOD_ID,"textures/gui/teapot_screen.png");
    private static final Rectangle PROGRESS_ARROW = new Rectangle(103, 26, 0, 17);
    private static final Rectangle HEAT_ICON = new Rectangle(35, 57 , 17, 15);


    public TeapotScreen(TeapotMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.titleLabelX = Math.round((this.imageWidth - this.font.width(this.title)) / 1.4F);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(graphics, mouseX, mouseY, delta);
        //фон
        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);

        //огонь отрисовывется если нагрет чайник
        if (((TeapotMenu)this.menu).isHeated()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176.0F, 0.0F, HEAT_ICON.width, HEAT_ICON.height, 256, 256);
        }

        //стрелка отрисовывается
        int l = ((TeapotMenu)this.menu).getCookProgressionScaled();
        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176.0F, 15.0F, l + 1, PROGRESS_ARROW.height, 256, 256);
    }
}
