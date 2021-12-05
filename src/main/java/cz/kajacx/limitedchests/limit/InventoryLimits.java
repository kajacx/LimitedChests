package cz.kajacx.limitedchests.limit;

import javax.annotation.Nonnull;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class InventoryLimits {

    private static final String NBT_KEY = LimitedChests.MODID + "_inventoryLimits";
    private static final int NBT_TYPE = 10; //TODO: find where this is defined in minecraft

    private @Nonnull SlotLimit[] slotLimits;

    public InventoryLimits(int size) {
        slotLimits = new SlotLimit[size];
        for (int i = 0; i < slotLimits.length; i++) {
            slotLimits[i] = new SlotLimit();
        }
    }

    public void clear(int index) {
        slotLimits[index].clear();
    }

    public void setCount(int index, int count) {
        slotLimits[index].setCount(count);
    }

    public void setFilter(int index, Item item) {
        slotLimits[index].setFilter(item);
    }

    public void setFilter(int index, Item item, int count) {
        slotLimits[index].setFilter(item, count);
    }

    public void setRestrict(int index, Item item) {
        slotLimits[index].setRestrict(item);
    }

    public void setRestrict(int index, Item item, int count) {
        slotLimits[index].setRestrict(item, count);
    }

    public void disable(int index) {
        slotLimits[index].disable();
    }

    public boolean isItemRestricted(Item item) {
        for (int i = 0; i < slotLimits.length; i++) {
            if (slotLimits[i].getRestrictedItem() == item) {
                return true;
            }
        }
        return false;
    }

    public int getMaxSize(int index) {
        if (index < 0 || index >= slotLimits.length) {
            Log.LOGGER.warn(Log.badArgsMarker, "InventoryLimits.getMaxSize index: {}", index);
            return 0;
        }

        return slotLimits[index].getMaxSize();
    }

    public int getMaxSize(int index, ItemStack stack) {
        if (index < 0 || index >= slotLimits.length || stack == null) {
            Log.LOGGER.warn(Log.badArgsMarker, "InventoryLimits.getMaxSize index: {}, stack: {}", index, stack);
            return 0;
        }

        boolean restricted = isItemRestricted(stack.getItem());
        return slotLimits[index].getMaxSize(stack.getItem(), restricted);
    }

    public CompoundNBT save(CompoundNBT compound) {
        ListNBT list = new ListNBT();
        for (int i = 0; i < slotLimits.length; i++) {
            list.add(slotLimits[i].save());
        }
        compound.put(NBT_KEY, list);
        return compound;
    }
    
    public void load(CompoundNBT compound) {
        try (Log log = Log.enter("SlotLimit.load", compound)) {
            if (!compound.contains(NBT_KEY)) {
                return;
            }
            ListNBT list = compound.getList(NBT_KEY, NBT_TYPE);
            for (int i = 0; i < slotLimits.length; i++) {
                slotLimits[i].load(list.getCompound(i));
            }
        } catch (Exception ex) {
            Log.LOGGER.catching(ex);
        }
    }
    
}
