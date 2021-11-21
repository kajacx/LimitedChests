package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.item.ModItems;
import net.minecraft.item.ItemStack;

import net.minecraft.item.ItemGroup;

public class TabLimitedChests extends ItemGroup {

    public static final TabLimitedChests instance = new TabLimitedChests(LimitedChests.MODID);

    public TabLimitedChests(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.chestLimiter.get());
    }
}
