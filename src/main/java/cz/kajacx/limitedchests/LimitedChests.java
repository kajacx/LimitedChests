
package cz.kajacx.limitedchests;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = cz.kajacx.limitedchests.LimitedChests.MODID, name = cz.kajacx.limitedchests.LimitedChests.NAME, version = cz.kajacx.limitedchests.LimitedChests.VERSION, useMetadata = true)
public class LimitedChests
{
    public static final String MODID = "limitedchests";
    public static final String NAME = "Limited Chests";
    public static final String VERSION = "1.12.0-0.1.0";

    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("HELLO LIMITED CHESTS, DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}

