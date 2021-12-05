package cz.kajacx.limitedchests.limit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cz.kajacx.limitedchests.util.Log;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class SlotLimit {
    private @Nonnull LimitType type = LimitType.ANY;

    /** Integer.MAX_VALUE For unlimited in FILTER or RESTRICT */
    private int count;

    /** non-null when type is FILTER or RESTRICT */
    private Item validItem;

    public void clear() {
        this.type = LimitType.ANY;
    }

    public void setCount(int count) {
        this.type = LimitType.COUNT;
        this.count = count;
    }

    public void setFilter(Item item) {
        setFilter(item, Integer.MAX_VALUE);
    }

    public void setFilter(Item item, int count) {
        this.type = LimitType.FILTER;
        this.validItem = item;
        this.count = count;
    }

    public void setRestrict(Item item) {
        setRestrict(item, Integer.MAX_VALUE);
    }

    public void setRestrict(Item item, int count) {
        this.type = LimitType.RESTRICT;
        this.validItem = item;
        this.count = count;
    }

    public void disable() {
        this.type = LimitType.DISABLE;
    }

    public int getMaxSize() {
        switch (type) {
            case ANY: return Integer.MAX_VALUE;
            case COUNT: return count;
            case FILTER: return count;
            case RESTRICT: return count;
            case DISABLE: return 0;
            default: return 0;
        }
    }

    public int getMaxSize(Item item, boolean restricted) {
        switch (type) {
            case ANY: return restricted ? 0 : Integer.MAX_VALUE;
            case COUNT: return restricted ? 0 : count;
            case FILTER: return item == validItem ? count : 0;
            case RESTRICT: return item == validItem ? count : 0;
            case DISABLE: return 0;
            default: return 0;
        }
    }

    public @Nullable Item getRestrictedItem() {
        if (type == LimitType.RESTRICT) {
            return validItem;
        } else {
            return null;
        }
    }

    public CompoundNBT save() {
        CompoundNBT compound = new CompoundNBT();
        compound.putString("type", type.name());
        compound.putInt("count", count);
        if (validItem != null) {
            CompoundNBT itemCompound = new CompoundNBT();
            (new ItemStack(validItem)).save(itemCompound);
            compound.put("validItem", itemCompound);
        }
        return compound;
    }
    
    public void load(CompoundNBT compound) {
        try (Log log = Log.enter("SlotLimit.load", compound)) {
            this.type = LimitType.valueOf(compound.getString("type"));
            this.count = compound.getInt("count");
            if (compound.contains("validItem")) {
                ItemStack stack = ItemStack.of(compound.getCompound("validItem"));
                this.validItem = stack.getItem();
            }
        } catch (Exception ex) {
            Log.LOGGER.catching(ex);
        }
    }
}
