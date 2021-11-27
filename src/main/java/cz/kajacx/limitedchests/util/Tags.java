package cz.kajacx.limitedchests.util;

import cz.kajacx.limitedchests.LimitedChests;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class Tags {
    
    public static final IOptionalNamedTag<Block> limitableBlocks =
        BlockTags.createOptional(new ResourceLocation(LimitedChests.MODID, "limitable_blocks"));

}
