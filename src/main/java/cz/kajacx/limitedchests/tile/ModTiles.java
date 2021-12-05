package cz.kajacx.limitedchests.tile;

import java.util.function.Supplier;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, LimitedChests.MODID);

    public static final RegistryObject<TileEntityType<TileLimitedChest>> LIMITED_CHEST =
        register("limited_chest", TileLimitedChest::new, ModBlocks.LIMITED_CHEST);

    public static final RegistryObject<TileEntityType<TileLimitedFurnace>> LIMITED_FURNACE =
        register("limited_furnace", TileLimitedFurnace::new, ModBlocks.LIMITED_FURNACE);

    public static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> constructor, RegistryObject<Block> block) {
       return TILES.register(name, () -> TileEntityType.Builder.of(constructor, block.get()).build(null));
    }

    public static void register(IEventBus eventBus) {
        try (Log log = Log.enter("ModTiles.register", eventBus)) {
            TILES.register(eventBus);
        }
    }
}
