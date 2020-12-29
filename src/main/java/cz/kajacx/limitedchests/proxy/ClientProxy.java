package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.client.render.blocks.BlockRenderRegister;
import cz.kajacx.limitedchests.client.render.items.ItemRenderRegister;
import cz.kajacx.limitedchests.client.render.tileentities.TileEntitiesRegister;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        BlockRenderRegister.preInit();
        ItemRenderRegister.preInit();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();
        TileEntitiesRegister.registerTileEntitiesRenderer();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
