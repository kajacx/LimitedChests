package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tileentities.LimitedChest;
import cz.kajacx.limitedchests.utils.Log;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiLimitedChest extends GuiContainer  {

    public GuiLimitedChest(IInventory playerInv, LimitedChest tileEntity) {
        super(new ContainerLimitedChest(playerInv, tileEntity));

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(new ResourceLocation("limitedchests", "textures/gui/limited_chest_gui.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
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
    }
}
