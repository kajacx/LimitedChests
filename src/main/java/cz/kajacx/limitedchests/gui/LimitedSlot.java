package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.limit.ILimitableTile;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class LimitedSlot extends Slot {
    private ILimitableTile container;

    public LimitedSlot(ILimitableTile container, int index, int x, int y) {
        super(container, index, x, y);
        this.container = container;
    }

    public LimitedSlot(Slot replacedSlot) {
        this((ILimitableTile) replacedSlot.container, replacedSlot.index, replacedSlot.x, replacedSlot.y);
    }

    @Override
    public int getMaxStackSize() {
        int maxContainerSize = super.getMaxStackSize();
        int maxLimitSize = container.getLimits().getMaxSize(index);
        return Math.min(maxContainerSize, maxLimitSize);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        if (stack == null) {
            Log.LOGGER.warn(Log.MARKER_ARGS, "LimitedChestSlot.getMaxStackSize stack: {}", stack);
            stack = ItemStack.EMPTY.copy();
        }

        int maxContainerSize = super.getMaxStackSize();
        int maxLimitSize = container.getLimits().getMaxSize(index);
        int maxItemSize = stack.getMaxStackSize();
        int max = Math.min(maxContainerSize, maxLimitSize);
        return Math.min(max, maxItemSize);
    }
}
