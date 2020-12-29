package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tileentities.LimitedChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiLimitedChest extends GuiContainer  {
    private IInventory playerInv;
    private LimitedChest te;

    public GuiLimitedChest(IInventory playerInv, LimitedChest te) {
        super(new ContainerLimitedChest(playerInv, te));

        this.xSize = 176;
        this.ySize = 166;

        this.playerInv = playerInv;
        this.te = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(new ResourceLocation("limitedchests", "textures/gui/limited_chest_gui.png"));
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.te.getDisplayName().getUnformattedText();
        fontRenderer.drawString(s, 88 - fontRenderer.getStringWidth(s) / 2, 6, 0x404040);
        fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 0x404040);
    }
}
