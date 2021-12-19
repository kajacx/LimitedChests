package cz.kajacx.limitedchests.proxy;

import cz.kajacx.limitedchests.screen.ModScreens;
import cz.kajacx.limitedchests.util.Log;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ProxyClient extends ProxyCommon {

    @Override
    public void register(IEventBus eventBus) {
        try (Log log = Log.enter("ProxyClient.register", eventBus)) {
            super.register(eventBus);
            eventBus.addListener(this::doClientStuff);
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        try (Log log = Log.enter("ProxyClient.doClientStuff", event)) {
            event.enqueueWork(() -> {
                ModScreens.register();
            });
        }
    }

}
