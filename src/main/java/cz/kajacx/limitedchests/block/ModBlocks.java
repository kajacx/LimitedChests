package cz.kajacx.limitedchests.block;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Log.TraceLog;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LimitedChests.MODID);

    public static final RegistryObject<Block> limitedChest = BLOCKS.register("limited_chest", 
        () -> new Block(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(0).harvestTool(ToolType.PICKAXE).strength(2.5f))
    );

    public static void register(IEventBus eventBus) {
        try (TraceLog log = Log.enter("ModBlocks.register", eventBus)) {
            BLOCKS.register(eventBus);
        }
    }
}
