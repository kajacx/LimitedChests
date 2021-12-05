package cz.kajacx.limitedchests.block;

import cz.kajacx.limitedchests.tile.ModTiles;
import cz.kajacx.limitedchests.tile.TileLimitedChest;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLimitedChest extends ChestBlock {

    public BlockLimitedChest(Properties properties) {
        super(properties, () -> ModTiles.LIMITED_CHEST.get());
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
       return new TileLimitedChest();
    }

}
