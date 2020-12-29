package cz.kajacx.limitedchests.tileentities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(LimitedChest.class, new ResourceLocation("limited_chest_tile_entity"));
    }

}
