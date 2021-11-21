package cz.kajacx.limitedchests.item;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.gui.TabLimitedChests;
import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.Log.TraceLog;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LimitedChests.MODID);

    public static final RegistryObject<Item> chestLimiter = ITEMS.register("chest_limiter",
        () -> new Item(new Item.Properties().tab(TabLimitedChests.instance))
    );

    public static final RegistryObject<Item> limitedChest = ITEMS.register("limited_chest",
        () -> new ItemLimitedChest(new Item.Properties().tab(TabLimitedChests.instance))
    );

    public static void register(IEventBus eventBus) {
        try (TraceLog log = Log.enter("ModItems.register", eventBus)) {
            ITEMS.register(eventBus);
        }
    }
}
