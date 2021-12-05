package cz.kajacx.limitedchests.container;

import cz.kajacx.limitedchests.gui.LimitedSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;

public class ContainerLimitedChest extends ChestContainer {

    public ContainerLimitedChest(ContainerType<?> menuType, int containerId, PlayerInventory playerInventory, int containerRows) {
        // TODO: this constructor seems odd ... refactor it?
        super(menuType, containerId, playerInventory, new Inventory(9 * containerRows), containerRows);

        for (int i = 0; i < 9 * containerRows; i++) {
            slots.set(i, new LimitedSlot(slots.get(i)));
        }
    }

    public static ContainerLimitedChest threeRows(int windowId, PlayerInventory inv, PacketBuffer data) {
        return new ContainerLimitedChest(ContainerType.GENERIC_9x3, windowId, inv, 3);
    }

}
