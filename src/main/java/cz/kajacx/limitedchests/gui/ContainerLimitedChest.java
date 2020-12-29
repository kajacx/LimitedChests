package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tileentities.LimitedChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLimitedChest extends Container {

    private LimitedChest te;

    public ContainerLimitedChest(IInventory playerInv, LimitedChest te) {
        this.te = te;

        // Tile Entity, Slot 0-8, Slot IDs 0-8
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlotToContainer(new LimitedChestSlot(te, x + y * 3, 62 + x * 18, 17 + y * 18));
            }
        }

        // Player Inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 36-44 (hotbar)
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 9) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 9, 45, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack2(current, 0, 9))
                    return null;
            }

            if (current.getCount() == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }

    private boolean mergeItemStack2(ItemStack stack, int startIndex, int endIndex) {
        if (startIndex <= endIndex)
            return false;

        boolean success = false;
        int index = startIndex;

        Slot slot;
        ItemStack stackInSlot;

        if (stack.isStackable()) {
            while (stack.getCount() > 0 && index < endIndex) {
                slot = this.inventorySlots.get(index);
                stackInSlot = slot.getStack();

                if (stackInSlot != null && stackInSlot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == stackInSlot.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, stackInSlot)) {
                    int l = stackInSlot.getCount() + stack.getCount();
                    int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));

                    if (l <= maxsize) {
                        stack.setCount(0);
                        stackInSlot.setCount(l);
                        slot.onSlotChanged();
                        success = true;
                    } else if (stackInSlot.getCount() < maxsize) {
                        stack.setCount(stack.getCount() - stack.getMaxStackSize() + stackInSlot.getCount());
                        stackInSlot.setCount(stack.getMaxStackSize());
                        slot.onSlotChanged();
                        success = true;
                    }
                }

                index++;
            }
        } else if (stack.getCount() > 0) {
            index = startIndex;

            while (index != endIndex && stack.getCount() > 0) {
                slot = this.inventorySlots.get(index);
                stackInSlot = slot.getStack();

                // Forge: Make sure to respect isItemValid in the slot.
                if (stackInSlot == null && slot.isItemValid(stack)) {
                    if (stack.getCount() < slot.getItemStackLimit(stack)) {
                        slot.putStack(stack.copy());
                        stack.setCount(0);
                        success = true;
                        break;
                    } else {
                        ItemStack newstack = stack.copy();
                        newstack.setCount(slot.getItemStackLimit(stack));
                        slot.putStack(newstack);
                        stack.setCount(stack.getCount() - slot.getItemStackLimit(stack));
                        success = true;
                    }
                }

                index++;
            }
        }

        return success;
    }

    /*public NonNullList<ItemStack> getInventory()
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>create();

        for (int i = 0; i < this.inventorySlots.size(); ++i)
        {
            ItemStack stack = ((Slot)this.inventorySlots.get(i)).getStack();
            if (stack != null) {
                nonnulllist.add(stack);
            }
        }

        return nonnulllist;
    }*/
}
