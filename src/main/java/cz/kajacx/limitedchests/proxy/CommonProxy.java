package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.blocks.ModBlocks;
import cz.kajacx.limitedchests.gui.ModGuiHandler;
import cz.kajacx.limitedchests.items.ModItems;
import cz.kajacx.limitedchests.tileentities.ModTileEntities;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.preInit();
        ModItems.preInit();
    }

    public void init(FMLInitializationEvent event) {
        //ModCrafting.initCrafting(event);
        ModTileEntities.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(LimitedChests.instance, new ModGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
