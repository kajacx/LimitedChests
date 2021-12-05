package cz.kajacx.limitedchests.container;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, LimitedChests.MODID);

    public static RegistryObject<ContainerType<ContainerLimitedChest>> LIMITED_CHEST = register("limited_chest", ContainerLimitedChest::threeRows);

    public static RegistryObject<ContainerType<ContainerLimitedFurnace>> LIMITED_FURNACE = register("limited_furnace", ContainerLimitedFurnace::create);

    public static <T extends Container>RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory) {
        return CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        try (Log log = Log.enter("ModContainers.register", eventBus)) {
            CONTAINERS.register(eventBus);
        }
    }

}
