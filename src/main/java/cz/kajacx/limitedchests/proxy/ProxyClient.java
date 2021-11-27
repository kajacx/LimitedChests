package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Log.TraceLog;
import net.minecraftforge.eventbus.api.IEventBus;

public class ProxyClient extends ProxyCommon {

    @Override
    public void register(IEventBus eventBus) {
        try (TraceLog log = Log.enter("ProxyClient.register", eventBus)) {
            super.register(eventBus);
        }
    }
}
