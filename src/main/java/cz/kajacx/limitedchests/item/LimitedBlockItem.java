package cz.kajacx.limitedchests.item;

import cz.kajacx.limitedchests.gui.TabLimitedChests;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class LimitedBlockItem extends BlockItem {

    public LimitedBlockItem(Block block) {
        super(block, new Properties().tab(TabLimitedChests.instance));
    }
}
