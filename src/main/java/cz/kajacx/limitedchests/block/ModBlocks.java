package cz.kajacx.limitedchests.block;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static Block limitedChestBlock = new BlockLimitedChest(Properties.of(Material.WOOD));

    public static void preInit() {
        GameRegistry.findRegistry(Block.class).register(limitedChestBlock);
    }
}
