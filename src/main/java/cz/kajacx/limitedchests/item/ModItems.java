package cz.kajacx.limitedchests.item;

import java.util.Properties;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.block.ModBlocks;
import cz.kajacx.limitedchests.gui.TabLimitedChests;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
//import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    //public static ItemType<ItemLimitedChest> itemLimitedChest = null;
    //public static Item limitedChest = new ItemLimitedChest(new Item.Properties());

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LimitedChests.MODID);

    public static final RegistryObject<Item> limitedChest = ITEMS.register("limited_chest",
        () -> new ItemLimitedChest(new Item.Properties().tab(/*TabLimitedChests.instance*/ItemGroup.TAB_DECORATIONS))
    );


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        /*limitedChestItem = new ItemBlock(ModBlocks.limitedChestBlock);
        limitedChestItem.setRegistryName("limited_chest_block");
        GameRegistry.findRegistry(Item.class).register(limitedChestItem);*/
    }
}
