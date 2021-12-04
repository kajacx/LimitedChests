package cz.kajacx.limitedchests.block;

import net.minecraft.block.DropperBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLimitedDropper extends DropperBlock {

    public BlockLimitedDropper(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
       return null;
    }

}
