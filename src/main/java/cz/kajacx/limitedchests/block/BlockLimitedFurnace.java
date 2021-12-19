package cz.kajacx.limitedchests.block;

import cz.kajacx.limitedchests.tile.TileLimitedFurnace;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockLimitedFurnace extends FurnaceBlock {

    public BlockLimitedFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
       return new TileLimitedFurnace();
    }

    @Override
    protected void openContainer(World level, BlockPos pos, PlayerEntity player) {
        try (Log log = Log.enter("BlockLimitedFurnace.openContainer", level, pos, player)) {
            if (level.isClientSide()) {
                return;
            }
            TileEntity tile = level.getBlockEntity(pos);
            if (tile instanceof TileLimitedFurnace) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (TileLimitedFurnace) tile, pos);
                player.awardStat(Stats.INTERACT_WITH_FURNACE);
            }
        }
    }

}
