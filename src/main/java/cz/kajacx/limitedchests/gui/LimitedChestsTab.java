package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class LimitedChestsTab extends CreativeTabs {

    public static final LimitedChestsTab instance = new LimitedChestsTab(LimitedChests.MODID);

    public LimitedChestsTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.limitedChestItem);
    }

}
