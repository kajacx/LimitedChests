package cz.kajacx.limitedchests.block;

import java.util.function.Function;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.item.LimitedBlockItem;
import cz.kajacx.limitedchests.item.ModItems;
import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Reflect;
import cz.kajacx.limitedchests.util.Log.TraceLog;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LimitedChests.MODID);

    public static final RegistryObject<Block> LIMITED_CHEST = registerBlock("limited_chest", Block::new, Blocks.CHEST);
    public static final RegistryObject<Block> LIMITED_FURNACE = registerBlock("limited_furnace", Block::new, Blocks.FURNACE);
    public static final RegistryObject<Block> LIMITED_HOPPER = registerBlock("limited_hopper", BlockLimitedHopper::new, Blocks.HOPPER);

    private static RegistryObject<Block> registerBlock(String name, Function<Properties, Block> constructor, Block baseBlock) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, () -> constructor.apply(copyProperties(baseBlock)));
        ModItems.ITEMS.register(name, () -> new LimitedBlockItem(registryObject.get()));
        return registryObject;
    }

    private static Properties copyProperties(Block baseBlock) {
        try {
            Properties copy = Properties.copy(baseBlock); //TODO: simply returning this copy doesn't work. WHY???
            Properties properties = Properties.of(baseBlock.defaultBlockState().getMaterial())
                .strength((float) Reflect.getField(copy, "destroyTime"), (float) Reflect.getField(copy, "explosionResistance"))
                .harvestLevel((int) Reflect.getField(copy, "harvestLevel"));
            if ((boolean) Reflect.getField(copy, "requiresCorrectToolForDrops")) {
                properties.requiresCorrectToolForDrops();
            }
            return properties;
        } catch (Exception ex) {
            Log.logger.warn(Log.exceptionMarker, "Failed to copy properties of block: '{}'", baseBlock);
            Log.logger.catching(ex);
            return Properties.of(Material.WOOD);
        }
    }

    public static void register(IEventBus eventBus) {
        try (TraceLog log = Log.enter("ModBlocks.register", eventBus)) {
            BLOCKS.register(eventBus);
        }
    }
}
