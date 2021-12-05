package cz.kajacx.limitedchests.container;

import cz.kajacx.limitedchests.gui.LimitedSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.network.PacketBuffer;

public class ContainerLimitedFurnace extends FurnaceContainer {

    public ContainerLimitedFurnace(int windowId, PlayerInventory playerInventory) {
        super(windowId, playerInventory);
        
        // TODO: hard-coded constant 3? And what about the other furnace constructor?
        for (int i = 0; i < 3; i++) {
            slots.set(i, new LimitedSlot(slots.get(i)));
        }
    }

    public static ContainerLimitedFurnace create(int windowId, PlayerInventory inv, PacketBuffer data) {
        return new ContainerLimitedFurnace(windowId, inv);
    }

}
