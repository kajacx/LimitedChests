package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TabLimitedChests extends ItemGroup {

    public static final TabLimitedChests INSTANCE = new TabLimitedChests(LimitedChests.MODID);

    private TabLimitedChests(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.CHEST_LIMITER.get());
    }

}
