package cz.kajacx.limitedchests.item;

import java.util.List;

import javax.annotation.Nullable;

import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.gui.TabLimitedChests;
import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Reflect;
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

    public ItemChestLimiter() {
        super(new Item.Properties().tab(TabLimitedChests.instance));
    }

    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.limitedchests.chest_limiter_line0"));
            tooltip.add(new TranslationTextComponent("tooltip.limitedchests.chest_limiter_line1"));
            tooltip.add(new TranslationTextComponent("tooltip.limitedchests.chest_limiter_line2"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.limitedchests.hold_shift"));
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    public ItemStack getContainerItem(ItemStack stack) {
        return new ItemStack(ModItems.CHEST_LIMITER.get(), 1);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World level = context.getLevel();

        if (level.isClientSide() || !context.getPlayer().isShiftKeyDown()) {
            return super.onItemUseFirst(stack, context);
        }

        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        
        Block replacedBlock = tryReplaceBlock(state.getBlock());
        if (replacedBlock != null) {
            level.setBlock(pos, replacedBlock.defaultBlockState(), 0);
            return ActionResultType.SUCCESS;
        }

        return super.onItemUseFirst(stack, context);
    }

    private @Nullable Block tryReplaceBlock(Block current) {
        // TODO: replace with actual block replacing
        if (!Tags.limitableBlocks.contains(current)) {
            return null;
        }
        if (current == ModBlocks.LIMITED_CHEST.get()) {
            return ModBlocks.LIMITED_FURNACE.get();
        }
        if (current == ModBlocks.LIMITED_FURNACE.get()) {
            return ModBlocks.LIMITED_HOPPER.get();
        }
        if (current == ModBlocks.LIMITED_HOPPER.get()) {
            return ModBlocks.LIMITED_DROPPER.get();
        }
        return ModBlocks.LIMITED_CHEST.get();
    }

}
