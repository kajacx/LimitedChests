package cz.kajacx.limitedchests.screen;

import cz.kajacx.limitedchests.container.ModContainers;
import net.minecraft.client.gui.ScreenManager;

public class ModScreens {
    
    public static void register() {
        ScreenManager.register(ModContainers.LIMITED_CHEST.get(), ScreenLimitedChest::new);
        ScreenManager.register(ModContainers.LIMITED_FURNACE.get(), ScreenLimitedFurnace::new);
    }

}
