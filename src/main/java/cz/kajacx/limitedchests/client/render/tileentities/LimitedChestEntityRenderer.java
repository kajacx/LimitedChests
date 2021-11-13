package cz.kajacx.limitedchests.client.render.tileentities;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.tileentities.LimitedChest;
import cz.kajacx.limitedchests.utils.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class LimitedChestEntityRenderer extends TileEntitySpecialRenderer<LimitedChest> {
    private static final ResourceLocation texture = new ResourceLocation("limitedchests", "textures/tileentities/limited_chest.png");
    private ModelChest model = new ModelChest();

    @Override
    public void render(LimitedChest tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        EnumFacing facing = tileEntity != null ? tileEntity.getFacing() : null;

        if (tileEntity == null || facing == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestEntityRenderer.render tileEntity: {}, facing: {}", tileEntity, facing);
            return;
        }

        // -- NORMAL RENDER CODE --

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z); //final move

        //model.chestLid.rotateAngleX = -1;

        //basic

        EnumFacing side = EnumFacing.getHorizontal(facing.getHorizontalIndex());
        float angleCW = side.getHorizontalIndex() - EnumFacing.SOUTH.getHorizontalIndex();
        angleCW *= 90;

        GlStateManager.translate(.5f, .5f, .5f); //move center back
        GlStateManager.rotate(angleCW, 0, -1, 0);

        GlStateManager.rotate(180, 1, 0, 0); //rotate from upside-down, now facing z+ (south)
        GlStateManager.translate(-.5f, -.5f, -.5f); //move center to 0

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        model.renderAll();

        GlStateManager.popMatrix();

        // -- END OF NORMAL RENDER CODE --
    }

}
