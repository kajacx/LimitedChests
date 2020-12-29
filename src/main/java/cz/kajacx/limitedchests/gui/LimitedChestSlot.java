package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tileentities.LimitedChest;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LimitedChestSlot extends Slot {
    private LimitedChest chest;

    public LimitedChestSlot(LimitedChest chest, int index, int xPosition, int yPosition) {
        super(chest, index, xPosition, yPosition);
        this.chest = chest;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return chest.isItemValidForSlot(slotNumber, stack);
    }

    @Override
    public int getSlotStackLimit() {
        return chest.getInventoryStackLimit(slotNumber);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return getSlotStackLimit();
    }

}
