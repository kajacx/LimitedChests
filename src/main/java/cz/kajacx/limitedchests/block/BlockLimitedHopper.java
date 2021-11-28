package cz.kajacx.limitedchests.block;

import net.minecraft.block.HopperBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLimitedHopper extends HopperBlock {

    public BlockLimitedHopper(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
       return null;
    }
}
