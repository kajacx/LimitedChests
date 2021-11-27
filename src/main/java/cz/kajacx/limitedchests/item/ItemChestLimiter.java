package cz.kajacx.limitedchests.item;

import java.util.List;

import javax.annotation.Nullable;

import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.util.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemChestLimiter extends Item {

    public ItemChestLimiter(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.limitedchests.chest_limiter"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.limitedchests.hold_shift"));
        }
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
        if (!Tags.limitableBlocks.contains(current)) {
            return null;
        }
        if (current == ModBlocks.limitedChest.get()) {
            return ModBlocks.limitedFurnace.get();
        }
        if (current == ModBlocks.limitedFurnace.get()) {
            return ModBlocks.limitedHopper.get();
        }
        return ModBlocks.limitedChest.get();
    }
}