package cz.kajacx.limitedchests.item;

import java.util.Properties;

import cz.kajacx.limitedchests.block.ModBlocks;
import net.minecraft.item.Item;
//import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    //public static ItemType<ItemLimitedChest> itemLimitedChest = null;
    public static Item limitedChest = new ItemLimitedChest(new Item.Properties());

    public static void preInit() {
        /*limitedChestItem = new ItemBlock(ModBlocks.limitedChestBlock);
        limitedChestItem.setRegistryName("limited_chest_block");
        GameRegistry.findRegistry(Item.class).register(limitedChestItem);*/
    }
}
