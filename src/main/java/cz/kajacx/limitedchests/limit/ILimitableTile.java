package cz.kajacx.limitedchests.limit;

import net.minecraft.inventory.IInventory;

public interface ILimitableTile extends IInventory {

    public InventoryLimits getLimits();

}
