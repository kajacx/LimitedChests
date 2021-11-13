package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tileentities.LimitedChest;
import cz.kajacx.limitedchests.utils.Log;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LimitedChestSlot extends Slot {
    private LimitedChest inventoryIn;

    public LimitedChestSlot(LimitedChest inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);

        if (inventoryIn == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestSlot.constructor inventoryIn: {}", inventoryIn);
            inventoryIn = new LimitedChest();
        }

        this.inventoryIn = inventoryIn;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        // no need to check stack, it will be checked in isItemValidForSlot
        return inventoryIn.isItemValidForSlot(slotNumber, stack);
    }

    @Override
    public int getSlotStackLimit() {
        return inventoryIn.getInventoryStackLimit(slotNumber);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return getSlotStackLimit();
    }

}
