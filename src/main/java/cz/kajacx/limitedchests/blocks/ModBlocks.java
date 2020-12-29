package cz.kajacx.limitedchests.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static Block limitedChestBlock;

    public static void preInit() {
        GameRegistry.findRegistry(Block.class).register(limitedChestBlock = new LimitedChestBlock("limited_chest_block"));
    }
}
