package cz.kajacx.limitedchests.tile;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModTiles {

    /*public static TileEntityType<TileLimitedChest> limitedChest = TileEntityType.Builder.of(TileLimitedChest::new, ModBlocks.limitedChestBlock).build(null);

    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        ResourceLocation location = new ResourceLocation(LimitedChests.MODID, "limited_chest");
        registry.register(limitedChest.setRegistryName(location));
    }*/
}
