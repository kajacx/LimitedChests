package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.client.render.blocks.BlockRenderRegister;
import cz.kajacx.limitedchests.client.render.items.ItemRenderRegister;
import cz.kajacx.limitedchests.client.render.tileentities.TileEntitiesRegister;
import net.minecraftforge.eventbus.api.IEventBus;

public class ProxyClient extends ProxyCommon {

    @Override
    public void register(IEventBus eventBus) {
        super.register(eventBus);
    }
}
