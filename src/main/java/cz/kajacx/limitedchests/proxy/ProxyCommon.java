package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.item.ModItems;
import cz.kajacx.limitedchests.tile.ModTiles;
import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Log.TraceLog;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class ProxyCommon {
    
    public void register(IEventBus eventBus) {
        try (TraceLog log = Log.enter("ProxyCommon.register", eventBus)) {
            ModItems.register(eventBus);
            ModBlocks.register(eventBus);
        }
    }
}
