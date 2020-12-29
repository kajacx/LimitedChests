package cz.kajacx.limitedchests.items;

import cz.kajacx.limitedchests.blocks.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
    public static ItemBlock limitedChestItem;

    public static void preInit() {
        limitedChestItem = new ItemBlock(ModBlocks.limitedChestBlock);
        limitedChestItem.setRegistryName("limited_chest_block");
        GameRegistry.findRegistry(Item.class).register(limitedChestItem);
    }
}
