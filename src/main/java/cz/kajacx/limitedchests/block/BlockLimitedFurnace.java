package cz.kajacx.limitedchests.block;

import net.minecraft.block.FurnaceBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLimitedFurnace extends FurnaceBlock {

    public BlockLimitedFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
       return null;
    }

}
