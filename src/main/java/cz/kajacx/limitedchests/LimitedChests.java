
package cz.kajacx.limitedchests;

import cz.kajacx.limitedchests.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = cz.kajacx.limitedchests.LimitedChests.MODID, name = cz.kajacx.limitedchests.LimitedChests.NAME, version = cz.kajacx.limitedchests.LimitedChests.VERSION)
public class LimitedChests
{
    public static final String MODID = "limitedchests";
    public static final String NAME = "Limited Chests";
    public static final String VERSION = "1.12.0-0.1.0";

    @Mod.Instance
    public static LimitedChests instance = new LimitedChests();

    public static Logger logger;

    @SidedProxy(clientSide = "cz.kajacx.limitedchests.proxy.ClientProxy", serverSide = "cz.kajacx.limitedchests.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.debug("Limited Chests preInit start");
        proxy.preInit(event);
        logger.debug("Limited Chests preInit end");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.debug("Limited Chests init start");
        proxy.init(event);
        logger.debug("Limited Chests init end");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.debug("Limited Chests postInit start");
        proxy.postInit(event);
        logger.debug("Limited Chests postInit end");
    }
}

