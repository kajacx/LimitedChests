package cz.kajacx.limitedchests.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import cz.kajacx.limitedchests.container.ContainerLimitedChest;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

//@OnlyIn(Dist.CLIENT)
public class ScreenLimitedChest extends ContainerScreen<ContainerLimitedChest> {
    private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation("minecraft:textures/gui/container/generic_54.png");
    private final int containerRows;
 
    public ScreenLimitedChest(ContainerLimitedChest menu, PlayerInventory inventory, ITextComponent title) {
       super(menu, inventory, title);
       this.passEvents = false;
       this.containerRows = menu.getRowCount();
       this.imageHeight = 114 + this.containerRows * 18;
       this.inventoryLabelY = this.imageHeight - 94;
    }
 
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
       this.renderBackground(matrixStack);
       super.render(matrixStack, mouseX, mouseY, partialTicks);
       this.renderTooltip(matrixStack, mouseX, mouseY);
    }
 
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
       RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
       this.minecraft.getTextureManager().bind(CONTAINER_BACKGROUND);
       int i = (this.width - this.imageWidth) / 2;
       int j = (this.height - this.imageHeight) / 2;
       this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
       this.blit(matrixStack, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }
 }
