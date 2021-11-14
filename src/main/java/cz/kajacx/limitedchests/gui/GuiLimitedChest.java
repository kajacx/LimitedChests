package cz.kajacx.limitedchests.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import cz.kajacx.limitedchests.tile.TileLimitedChest;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class GuiLimitedChest extends ContainerScreen<ContainerLimitedChest>  {
    
	public ResourceLocation textureFile = new ResourceLocation("limitedchests", "textures/gui/limited_chest.png");

    public GuiLimitedChest(ContainerLimitedChest container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);

        this.width = 176;
        this.height = 166;
    }

    @Override
	protected void renderBg(MatrixStack transform, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F); //TODO: deprecated?

		TextureManager textureManager = Minecraft.getInstance().getTextureManager();
		textureManager.bind(textureFile);

		blit(transform, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    /*@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        if (!(inventorySlots instanceof ContainerLimitedChest)) {
            Log.logger.warn(Log.badFieldsMarker, "GuiLimitedChest.drawGuiContainerForegroundLayer inventorySlots: {}", inventorySlots);
            return ;
        }

        ContainerLimitedChest container = (ContainerLimitedChest) inventorySlots;
        String name = container.getTileEntity().getDisplayName().getUnformattedText();
        fontRenderer.drawString(name, 88 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);

        IInventory playerInv = container.getPlayerInv();
        if (playerInv == null) {
            // no need to log, the log was made when playerInv was set in ContainerLimitedChest constructor
            return;
        }
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, 72, 0x404040);
    }*/
}
