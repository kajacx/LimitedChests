package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.util.Log;
import net.minecraftforge.eventbus.api.IEventBus;

public class ProxyServer extends ProxyCommon {

    @Override
    public void register(IEventBus eventBus) {
        try (Log log = Log.enter("ProxyServer.register", eventBus)) {
            super.register(eventBus);
        }
    }

}
