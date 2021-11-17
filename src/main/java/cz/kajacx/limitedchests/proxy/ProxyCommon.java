package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.item.ModItems;
import cz.kajacx.limitedchests.tile.ModTiles;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class ProxyCommon {
    
    public void register(IEventBus eventBus) {
        ModItems.register(eventBus);
    }
}
