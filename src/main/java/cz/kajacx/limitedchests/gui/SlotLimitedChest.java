package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tile.TileLimitedChest;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotLimitedChest extends Slot {
    private TileLimitedChest inventoryIn;

    public SlotLimitedChest(TileLimitedChest inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);

        if (inventoryIn == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestSlot.constructor inventoryIn: {}", inventoryIn);
            inventoryIn = new TileLimitedChest();
        }

        if (index < 0 || index >= inventoryIn.getContainerSize()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestSlot.constructor index: {}", index);
            index = 0;
        }

        this.inventoryIn = inventoryIn;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        // no need to check stack, it will be checked in isItemValidForSlot
        return inventoryIn.canPlaceItem(index, stack);
    }

    @Override
    public int getMaxStackSize() {
        return inventoryIn.getMaxStackSize(index);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return inventoryIn.getMaxStackSize(index, stack);
    }
}
