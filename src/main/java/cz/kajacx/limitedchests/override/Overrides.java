package cz.kajacx.limitedchests.override;

import cz.kajacx.limitedchests.limit.InventoryLimits;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class Overrides {

    public static void setItem(ISetItem parent, InventoryLimits limits, int index, ItemStack stack) {
        int maxSpace = limits.getMaxSize(index, stack);
        if (maxSpace == 0) {
            return;
        }

        parent.setItem(index, stack);
        
        if (maxSpace < stack.getCount()) {
            stack.setCount(maxSpace);
        }
    }

    public static boolean canPlaceItem(ICanPlaceItem parent, InventoryLimits limits, int index, ItemStack stack) {
        return parent.canPlaceItem(index, stack) && limits.getMaxSize(index, stack) > 0;
    }

    public static CompoundNBT save(ISave parent, InventoryLimits limits, CompoundNBT compound) {
        compound = parent.save(compound);
        return limits.save(compound);
    }

    public static void load(ILoad parent, InventoryLimits limits, BlockState state, CompoundNBT compound) {
        parent.load(state, compound);
        limits.load(compound);
    }

}
