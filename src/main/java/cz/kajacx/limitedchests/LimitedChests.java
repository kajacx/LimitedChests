
package cz.kajacx.limitedchests;

import cz.kajacx.limitedchests.proxy.ProxyClient;
import cz.kajacx.limitedchests.proxy.ProxyCommon;
import cz.kajacx.limitedchests.proxy.ProxyServer;
import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Log.TraceLog;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(cz.kajacx.limitedchests.LimitedChests.MODID)
public class LimitedChests {

    public static final String MODID = "limitedchests";
    public static final String NAME = "Limited Chests";
    public static final String VERSION = "1.16.5-0.1.0";

    public static ProxyCommon proxy;

    public LimitedChests() {
        try (TraceLog log = Log.enter("LimitedChests.constructor")) {
            DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> proxy = new ProxyClient());
            DistExecutor.unsafeCallWhenOn(Dist.DEDICATED_SERVER, () -> () -> proxy = new ProxyServer());
            
            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
            proxy.register(eventBus);

            MinecraftForge.EVENT_BUS.register(this);
        }
    }

}
