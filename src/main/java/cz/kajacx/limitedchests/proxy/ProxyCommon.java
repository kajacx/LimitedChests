package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.container.ModContainers;
import cz.kajacx.limitedchests.item.ModItems;
import cz.kajacx.limitedchests.tile.ModTiles;
import cz.kajacx.limitedchests.util.Log;
import net.minecraftforge.eventbus.api.IEventBus;

public abstract class ProxyCommon {
    
    public void register(IEventBus eventBus) {
        try (Log log = Log.enter("ProxyCommon.register", eventBus)) {
            ModItems.register(eventBus);
            ModBlocks.register(eventBus);
            ModTiles.register(eventBus);
            ModContainers.register(eventBus);
        }
    }

}
