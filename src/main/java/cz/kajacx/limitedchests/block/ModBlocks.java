package cz.kajacx.limitedchests.block;

import java.util.function.Function;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.item.LimitedBlockItem;
import cz.kajacx.limitedchests.item.ModItems;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LimitedChests.MODID);

    public static final RegistryObject<Block> LIMITED_CHEST = register("limited_chest", BlockLimitedChest::new, Blocks.CHEST);
    public static final RegistryObject<Block> LIMITED_FURNACE = register("limited_furnace", BlockLimitedFurnace::new, Blocks.FURNACE);
    public static final RegistryObject<Block> LIMITED_HOPPER = register("limited_hopper", BlockLimitedHopper::new, Blocks.HOPPER);
    public static final RegistryObject<Block> LIMITED_DROPPER = register("limited_dropper", BlockLimitedDropper::new, Blocks.DROPPER);

    public static RegistryObject<Block> register(String name, Function<Properties, Block> constructor, Block baseBlock) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, () -> constructor.apply(Properties.copy(baseBlock)));
        ModItems.ITEMS.register(name, () -> new LimitedBlockItem(registryObject.get()));
        return registryObject;
    }

    public static void register(IEventBus eventBus) {
        try (Log log = Log.enter("ModBlocks.register", eventBus)) {
            BLOCKS.register(eventBus);
        }
    }

}
