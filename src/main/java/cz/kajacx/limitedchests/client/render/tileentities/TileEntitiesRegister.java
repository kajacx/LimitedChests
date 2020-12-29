package cz.kajacx.limitedchests.client.render.tileentities;

import cz.kajacx.limitedchests.blocks.ModBlocks;
import cz.kajacx.limitedchests.client.render.blocks.BlockRenderRegister;
import cz.kajacx.limitedchests.tileentities.LimitedChest;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TileEntitiesRegister {

    public static void registerTileEntitiesRenderer() {
        BlockRenderRegister.reg(ModBlocks.limitedChestBlock);

        ClientRegistry.bindTileEntitySpecialRenderer(LimitedChest.class, new LimitedChestEntityRenderer());
    }
}
