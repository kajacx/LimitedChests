
package cz.kajacx.limitedchests;

import cz.kajacx.limitedchests.proxy.ProxyClient;
import cz.kajacx.limitedchests.proxy.ProxyCommon;
import cz.kajacx.limitedchests.proxy.ProxyServer;
import cz.kajacx.limitedchests.util.Log;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(cz.kajacx.limitedchests.LimitedChests.MODID)
public class LimitedChests {

    public static final String MODID = "limitedchests";
    public static final String NAME = "Limited Chests";
    public static final String VERSION = "1.16.5-0.1.0";

    public static ProxyCommon proxy;

    public LimitedChests() {
        Log.logger.debug("Limited Chests main constructor start");

		DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> proxy = new ProxyClient());
		DistExecutor.unsafeCallWhenOn(Dist.DEDICATED_SERVER, () -> () -> proxy = new ProxyServer());
        proxy.registerHandlers();

        Log.logger.debug("Limited Chests main constructor end");
    }
}

