package cz.kajacx.limitedchests.item;

import javax.annotation.Nullable;

import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChestLimiter extends Item {

    public ItemChestLimiter(Properties properties) {
        super(properties);
    }
    
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World level = context.getLevel();

        if (!level.isClientSide()) {
            BlockPos pos = context.getClickedPos();
            BlockState state = level.getBlockState(pos);
            
            Block replacedBlock = tryReplaceBlock(state.getBlock());
            if (replacedBlock != null) {
                level.setBlock(pos, replacedBlock.defaultBlockState(), 0);
                return ActionResultType.SUCCESS;
            }
        }

        return super.onItemUseFirst(stack, context);
    }

    private @Nullable Block tryReplaceBlock(Block current) {
        // TODO: replace with actual block replacing
        if (current == ModBlocks.limitedChest.get()) {
            return ModBlocks.limitedFurnace.get();
        }
        if (current == ModBlocks.limitedFurnace.get()) {
            return ModBlocks.limitedChest.get();
        }
        return null;
    }

}
