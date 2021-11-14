package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tile.TileLimitedChest;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerLimitedChest extends Container {

    private TileLimitedChest tileEntity;
    private IInventory playerInventory;

    public ContainerLimitedChest(ContainerType<?> type, int windowIn) {
        super(type, windowIn);

        // Tile Entity, Slot 0-8, Slot IDs 0-8
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                addSlot(new SlotLimitedChest(tileEntity, x + y * 3, 62 + x * 18, 17 + y * 18));
            }
        }
    }

    public void addPlayerInventory(PlayerInventory playerInventory, int xInv, int yInv) {
        this.playerInventory = playerInventory;

        if (playerInventory == null) {
            Log.logger.warn(Log.badArgsMarker, "ContainerLimitedChest.constructor playerInv: {}", playerInventory);
            return;
        }

		// Player inventory
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(playerInventory, column + row * 9 + 9, xInv + column * 18, yInv + row * 18);
			}
		}
		// Player hotbar
		for (int column = 0; column < 9; column++) {
			addSlot(playerInventory, column, xInv + column * 18, yInv + 58);
		}
	}

	public void addSlot(PlayerInventory playerInventory, int slot, int x, int y) {
		super.addSlot(new Slot(playerInventory, slot, x, y));
	}

    public TileLimitedChest getTileEntity() {
        return tileEntity;
    }

    public IInventory getPlayerInventory() {
        return playerInventory;
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        if (playerIn == null) {
            Log.logger.warn(Log.badArgsMarker, "ContainerLimitedChest.canInteractWith facing: {}", playerIn);
            return false;
        }

        return tileEntity.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int fromSlot) {
        if (playerIn == null) {
            Log.logger.warn(Log.badArgsMarker, "ContainerLimitedChest.transferStackInSlot playerIn: {}", playerIn);
            return ItemStack.EMPTY.copy();
        }

		if (stillValid(playerIn)) {
			return ItemStack.EMPTY;
		}

        /*ItemStack previous = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 9) {
                // From TE Inventory to Player Inventory
                if (!mergeItemStack(current, 9, 45, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (!mergeItemStack2(current, 0, 9))
                    return null;
            }

            if (current.getCount() == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (current.getCount() == previous.getCount()) {
                return null;
            }
            slot.onTake(playerIn, current);
        }
        return previous;*/
        return ItemStack.EMPTY.copy();
    }

    /*private boolean mergeItemStack2(ItemStack stack, int startIndex, int endIndex) {
        if (startIndex <= endIndex)
            return false;

        boolean success = false;
        int index = startIndex;

        Slot slot;
        ItemStack stackInSlot;

        if (stack.isStackable()) {
            while (stack.getCount() > 0 && index < endIndex) {
                slot = inventorySlots.get(index);
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
                slot = inventorySlots.get(index);
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

    public NonNullList<ItemStack> getInventory()
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>create();

        for (int i = 0; i < inventorySlots.size(); ++i)
        {
            ItemStack stack = ((Slot)inventorySlots.get(i)).getStack();
            if (stack != null) {
                nonnulllist.add(stack);
            }
        }

        return nonnulllist;
    }*/
}
