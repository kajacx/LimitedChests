package cz.kajacx.limitedchests.override;

import net.minecraft.item.ItemStack;

public interface ICanPlaceItem {
    public boolean canPlaceItem(int index, ItemStack stack);
}
