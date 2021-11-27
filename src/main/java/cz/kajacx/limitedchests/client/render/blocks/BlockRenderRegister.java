package cz.kajacx.limitedchests.client.render.blocks;


import net.minecraft.block.Block;

public final class BlockRenderRegister {

    public static void preInit() {

    }

    public static void registerBlockRenderer() {

    }

    public static void reg(Block block) {
        //Minecraft.getInstance().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(LimitedChests.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

    //public static void reg(Block block, int meta, String file) {
    //    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(LimitedChests.MODID + ":" + file, "inventory"));
    //}
}
